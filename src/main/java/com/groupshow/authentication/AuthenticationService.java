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
import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.transaction.TransactionalException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

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

    public Boolean resetPassword(ResetPasswordRequestDto request) throws UserNotFoundException, InvalidCredentialsException {
        if (request.getNewPassword().equals(request.getConfirmNewPassword())) {
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UserNotFoundException("email"));

            if (user.isEnabled() && user.getPassword().equals(request.getCurrentPassword())) {
                String hashedPassword = passwordEncoder.encode(request.getNewPassword());

                user.setPassword(hashedPassword);

                userRepository.save(user);
                return true;
            } else {
                throw new InvalidCredentialsException();
            }
        }
        return false;
    }

    public AuthenticateUserDto authenticateUser(AuthenticationRequestDto request)
            throws UserNotFoundException, UserIsLoggedInException, InvalidCredentialsException {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("email"));

        if (user.isAccountNonExpired()) {
            throw new UserIsLoggedInException();
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        revokeAllUserTokens(user.getEmail());

        String accessJwt = jwtService.generateToken(user, TokenType.ACCESS);
        Token accessToken = createNewToken(accessJwt, TokenType.ACCESS, user);

        String refreshJwt = jwtService.generateToken(user, TokenType.REFRESH);
        Token refreshToken = createNewToken(refreshJwt, TokenType.REFRESH, user);

        return AuthenticateUserDto.builder()
                .user(user)
                .accessJwt(accessToken.getJwt())
                .accessJwtExpiresOn(accessToken.getExpiresOn())
                .refreshJwt(refreshToken.getJwt())
                .refreshJwtExpiresOn(refreshToken.getExpiresOn())
                .build();
    }

    public String refreshAccessToken(String refreshJwt) throws UserNotFoundException {
        Token userRefreshToken = tokenRepository.findByJwt(refreshJwt)
                .orElseThrow(() -> new UserNotFoundException("refresh token"));
        var user = userRefreshToken.getUser();

        if (jwtService.isTokenValid(refreshJwt, user)) {
            // Find and revoke the user's existing access token
            user.getTokens().stream()
                    .filter(token -> token.getTokenType().equals(TokenType.ACCESS))
                    .forEach(token -> {
                        token.setIsExpired(true);
                        token.setIsRevoked(true);
                    });

            String newAccessJwt = jwtService.generateToken(user, TokenType.ACCESS);
            createNewToken(newAccessJwt, TokenType.ACCESS, user);
            return newAccessJwt;
        }
        return null;
    }

    public Boolean forgotPassword(String userEmail) {
        try {
            Emailer.sendPasswordResetEmail(userEmail);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Token createNewToken(String jwt, TokenType tokenType, User user) {
        var expDate = jwtService.extractExpiration(jwt);
        var expDateTime = LocalDateTime.ofInstant(expDate.toInstant(), ZoneId.systemDefault());

        var newToken = Token.builder()
                .jwt(jwt)
                .tokenType(tokenType)
                .expiresOn(expDateTime)
                .isExpired(false)
                .isRevoked(false)
                .user(user)
                .build();

        return tokenRepository.save(newToken);
    }

    public void revokeAllUserTokens(String email) {
        var userTokens = tokenRepository.findAllValidTokensByUser(email);

        if (!userTokens.isEmpty()) {
            userTokens.forEach(token -> {
                token.setIsExpired(true);
                token.setIsRevoked(true);
            });

            try {
                tokenRepository.saveAll(userTokens);
            } catch (TransactionalException e) {
                e.printStackTrace();
            }
        }
    }
}
