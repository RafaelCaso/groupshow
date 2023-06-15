package com.groupshow.authentication;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import com.groupshow.security.JwtService;
import com.groupshow.utilities.Emailer;
import com.groupshow.utilities.TokenGenerator;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        if (resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmNewPassword())) {
            User activatedUser = userRepository.findByEmail(resetPasswordRequest.getEmail()).orElseThrow(() -> new Exception("A user with this email was not found."));

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

        String jwtToken = jwtService.generateToken(authenticatedUser);

        var authResponse = AuthenticationResponseDto.builder()
                .user(authenticatedUser)
                .jwtToken(jwtToken)
                .build();

        return authResponse;
    }

    // logout service
        // revoke jwt?
}
