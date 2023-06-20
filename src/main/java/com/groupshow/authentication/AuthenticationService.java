package com.groupshow.authentication;

import com.groupshow.exceptions.InvalidCredentialsException;
import com.groupshow.exceptions.UserIsLoggedInException;
import com.groupshow.exceptions.UserNotFoundException;
import com.groupshow.token.Token;
import com.groupshow.token.TokenRepository;
import com.groupshow.token.TokenType;
import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import com.groupshow.security.JwtService;
import com.groupshow.utilities.Emailer;
import com.groupshow.utilities.TokenGenerator;

import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;

    public Boolean registerNewUser(RegisterRequestDto regRequest) throws UserNotFoundException {
        String defaultPassword = TokenGenerator.createNewToken();
        String registrationToken = TokenGenerator.createNewToken();

        var newUser = User.builder()
                .userRole(regRequest.getUserRole())
                .firstName(regRequest.getFirstName())
                .lastName(regRequest.getLastName())
                .email(regRequest.getEmail())
                .password(defaultPassword)
                .gradeLevel(regRequest.getGradeLevel())
                .major(regRequest.getMajor())
                .minor(regRequest.getMinor())
                .registrationToken(registrationToken)
                .isAccountActivated(false)
                .build();

        userRepository.save(newUser);

        var savedUser = userRepository.findById(newUser.getUserID())
                .orElseThrow(() -> new UserNotFoundException("ID"));

        try {
            Emailer.sendRegistrationEmail(savedUser);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean activateNewUser(Integer userID, String registrationToken) throws UserNotFoundException {
        var user = userRepository.findById(userID)
                .orElseThrow(() -> new UserNotFoundException("ID"));

        if (user.getRegistrationToken().equals(registrationToken) && !user.getIsAccountActivated()) {
            user.setIsAccountActivated(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Boolean resetPassword(ResetPasswordRequestDto resetPasswordRequest) throws UserNotFoundException, InvalidCredentialsException {
        // Checks that new password is written correctly
        if (resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmNewPassword())) {
            var user = userRepository.findByEmail(resetPasswordRequest.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("email"));

            // Checks that user knows their original password in order to reset it
            if (user.getIsAccountActivated() && user.getPassword().equals(resetPasswordRequest.getCurrentPassword())) {
                user.setPassword(resetPasswordRequest.getNewPassword());
                userRepository.save(user);
                return true;
            } else {
                throw new InvalidCredentialsException();
            }
        }
        return false;
    }

    public AuthenticationResponseDto login(AuthenticationRequestDto authRequest) throws UserNotFoundException, UserIsLoggedInException, InvalidCredentialsException {
        var user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("email"));

        if (!user.getTokens().isEmpty()) {
            throw new UserIsLoggedInException();
        }

        if (!user.getPassword().equals(authRequest.getPassword())) {
            throw new InvalidCredentialsException();
        }

        revokeAllUserTokens(user);

        String accessJwt = jwtService.generateToken(user, TokenType.ACCESS);
        saveToken(accessJwt, TokenType.ACCESS, user);

        String refreshJwt = jwtService.generateToken(user, TokenType.REFRESH);
        saveToken(refreshJwt, TokenType.REFRESH, user);

        return AuthenticationResponseDto.builder()
                .user(user)
                .accessJwt(accessJwt)
                .refreshJwt(refreshJwt)
                .build();
    }

    public String refreshAccessToken(String refreshJwt) throws UserNotFoundException {
        // Use the refresh jwt to find the saved refresh token in db
        var userRefreshToken = tokenRepository.findByJwt(refreshJwt)
                .orElseThrow(() -> new UserNotFoundException("refresh token"));

        // Use the saved refresh token to find its user
        var user = userRefreshToken.getUser();

        if (jwtService.isTokenValid(refreshJwt, user)) {
            // Find and revoke the user's existing access token
            user.getTokens()
                    .stream()
                    .filter(token -> token.getTokenType().equals(TokenType.ACCESS))
                    .forEach(token -> {
                        token.setIsExpired(true);
                        token.setIsRevoked(true);
                    });

            // Create a new access jwt
            String newAccessJwt = jwtService.generateToken(user, TokenType.ACCESS);

            // Create a new access token and save it in db
            saveToken(newAccessJwt, TokenType.ACCESS, user);
            return newAccessJwt;
        }
        return null;
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getUserID());

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setIsExpired(true);
            token.setIsRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    public Boolean forgotPassword(String userEmail) {
        try {
            Emailer.sendPasswordResetEmail(userEmail);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void saveToken(String token, TokenType tokenType, User user) {
        var newToken = Token.builder()
                .token(token)
                .tokenType(tokenType)
                .isExpired(false)
                .isRevoked(false)
                .user(user)
                .build();

        tokenRepository.save(newToken);
    }
}
