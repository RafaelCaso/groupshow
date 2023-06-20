package com.groupshow.authentication;

import com.groupshow.exceptions.UserNotFoundException;
import com.groupshow.security.LogoutService;
import com.groupshow.user.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private LogoutService logoutService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerNewUser(@RequestBody RegisterRequestDto regRequest) throws Exception {
        return ResponseEntity.ok(authService.registerNewUser(regRequest));
    }

    @GetMapping("/activate-account")
    public ResponseEntity<Boolean> activateNewUser(
            @RequestParam(name = "userID") Integer userID,
            @RequestParam(name = "regToken") String registrationToken)
            throws Exception {
        return ResponseEntity.ok(authService.activateNewUser(userID, registrationToken));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordRequestDto resetPasswordRequest) throws Exception {
        return ResponseEntity.ok(authService.resetPassword(resetPasswordRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthenticationRequestDto authRequest, HttpServletResponse response) throws Exception {
        var authResponseDto = authService.login(authRequest);

        var headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers", "Authorization, X-Refresh-Token");
        headers.add("Authorization", "Bearer " + authResponseDto.getAccessJwt());
        headers.add("X-Refresh-Token", authResponseDto.getRefreshJwt().toString());

        return ResponseEntity.ok()
                .headers(headers)
                .body(authResponseDto.getUser());
    }

    @GetMapping("/refresh-access")
    public ResponseEntity<String> refreshAccessToken(@RequestHeader("X-Refresh-Token") String refreshToken) throws UserNotFoundException {
        return ResponseEntity.ok(authService.refreshAccessToken(refreshToken));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestParam(name = "email") String userEmail) throws IOException {
        return ResponseEntity.ok(authService.forgotPassword(userEmail));
    }
}
