package com.groupshow.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerNewUser(@RequestBody RegisterRequestDto regRequest) throws Exception {
        return ResponseEntity.ok(authService.registerNewUser(regRequest));
    }

    @GetMapping("/activate")
    public ResponseEntity<Boolean> activateNewUser(@RequestParam(name="regToken")String registrationToken) throws Exception {
        return ResponseEntity.ok(authService.activateNewUser(registrationToken));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(ResetPasswordRequestDto resetPasswordRequest) throws Exception {
        return ResponseEntity.ok(authService.resetPassword(resetPasswordRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authRequest) throws Exception {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    // logout route
        // revoke jwt?
}
