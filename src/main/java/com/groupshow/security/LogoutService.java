package com.groupshow.security;

import com.groupshow.authentication.AuthenticationService;
import com.groupshow.exceptions.UserIsLoggedInException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication
    ) {
        System.out.println("REQUEST: " + request);
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String accessJwt = authHeader.substring(7);
        final String userEmail = jwtService.extractUsername(accessJwt);

        authenticationService.revokeAllUserTokens(userEmail);
    }
}
