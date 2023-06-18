package com.groupshow.authentication;

import com.groupshow.security.JwtTokenType;
import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import com.groupshow.security.JwtService;
import com.groupshow.utilities.Emailer;
import com.groupshow.utilities.TokenGenerator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Boolean registerNewUser(RegisterRequestDto regRequest) {
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

        User savedUser = userRepository.findById(newUser.getUserID()).get();

        try {
            Emailer.sendRegistrationEmail(savedUser);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean activateNewUser(Integer userID, String registrationToken) throws Exception {
        User registeredUser = userRepository.findById(userID).orElseThrow(() -> new Exception("User not found."));

        if (registeredUser.getRegistrationToken().equals(registrationToken) && !registeredUser.getIsAccountActivated()) {
            registeredUser.setIsAccountActivated(true);
            userRepository.save(registeredUser);
            return true;
        }
        return false;
    }

    public Boolean resetPassword(ResetPasswordRequestDto resetPasswordRequest) throws Exception {
        // Checks that new password is written correctly
        if (resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmNewPassword())) {
            User activatedUser = userRepository.findByEmail(resetPasswordRequest.getEmail()).orElseThrow(() -> new Exception("A user with this email was not found."));

            // Checks that user knows their original password - authorization check to reset it
            if (activatedUser.getPassword().equals(resetPasswordRequest.getCurrentPassword())) {
                activatedUser.setPassword(resetPasswordRequest.getNewPassword());
                userRepository.save(activatedUser);
                return true;
            }
        }
        return false;
    }

    public AuthenticationResponseDto login(AuthenticationRequestDto authRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        User authenticatedUser = userRepository.findByEmail(authRequest.getEmail()).orElseThrow(() -> new Exception("A user with this email was not found."));

        if (!authenticatedUser.getPassword().equals(authRequest.getPassword())) {
            throw new RuntimeException("Invalid credentials.");
        }

        String jwtAccessToken = jwtService.generateToken(authenticatedUser, JwtTokenType.ACCESS);
        String jwtRefreshToken = jwtService.generateToken(authenticatedUser, JwtTokenType.REFRESH);

        return AuthenticationResponseDto.builder()
                .user(authenticatedUser)
                .jwtAccessToken(jwtAccessToken)
                .jwtRefreshToken(jwtRefreshToken)
                .build();
    }

    public String refreshAccessToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String[] tokens = authHeader.substring(7).split(" ");
            String refreshToken = tokens[1];

            // Get userDetails from refreshToken
            try {
                var authenticationToken = new UsernamePasswordAuthenticationToken(null, refreshToken);
                Authentication authentication = authenticationManager.authenticate(authenticationToken);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                if (jwtService.isTokenValid(refreshToken, userDetails)) {
                    String newAccessToken = jwtService.generateToken(userDetails, JwtTokenType.ACCESS);
                    return newAccessToken;
                }
            } catch (AuthenticationException e) {
                return null;
            }
        }
        return null;
    }

    public Boolean logout() {
        // revoke jwt and any other cleanup?
        return true;

        // else return false/throw error message
    }

    public Boolean forgotPassword(String userEmail) throws IOException {
        try {
            Emailer.sendPasswordResetEmail(userEmail);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
