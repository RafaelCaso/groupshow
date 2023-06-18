package com.groupshow.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerNewUser(@RequestBody RegisterRequestDto regRequest) {
        return ResponseEntity.ok(authService.registerNewUser(regRequest));
    }

    @GetMapping("/activate-account")
    public ResponseEntity<Boolean> activateNewUser(@RequestParam(name = "userID") Integer userID, @RequestParam(name = "regToken") String registrationToken) throws Exception {
        return ResponseEntity.ok(authService.activateNewUser(userID, registrationToken));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordRequestDto resetPasswordRequest) throws Exception {
        return ResponseEntity.ok(authService.resetPassword(resetPasswordRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authRequest) throws Exception {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @GetMapping("/refresh-access")
    public ResponseEntity<String> refreshAccessToken(@RequestHeader("Authorization") String authHeader) {
        return ResponseEntity.ok(authService.refreshAccessToken(authHeader));
    }

    @GetMapping("/logout")
    // pass jwt here? where does jwt get extracted from header/where can I use it?
    public ResponseEntity<Boolean> logout() {
        return ResponseEntity.ok(authService.logout());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestParam(name = "email") String userEmail) throws IOException {
        return ResponseEntity.ok(authService.forgotPassword(userEmail));
    }

}
