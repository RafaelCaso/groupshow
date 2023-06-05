package com.groupshow.services;

import com.groupshow.models.User;
import com.groupshow.models.UserType;
import com.groupshow.repositories.UserRepository;
import com.groupshow.security.JwtService;
import com.groupshow.utilities.dto.AuthenticationRequestDto;
import com.groupshow.utilities.dto.AuthenticationResponseDto;
import com.groupshow.utilities.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(UserType.ADMIN)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
