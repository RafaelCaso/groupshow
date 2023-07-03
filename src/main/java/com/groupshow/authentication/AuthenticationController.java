package com.groupshow.authentication;

import com.groupshow.exceptions.InvalidCredentialsException;
import com.groupshow.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerNewUser(@RequestBody RegisterRequestDto request) throws Exception {
        return ResponseEntity.ok(authService.registerNewUser(request));
    }

    @GetMapping("/activate-account")
    public ResponseEntity<Boolean> activateNewUser(
            @RequestParam(name = "userID") Integer userID,
            @RequestParam(name = "regToken") String registrationToken)
            throws UserNotFoundException {
        return ResponseEntity.ok(authService.activateNewUser(userID, registrationToken));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody ResetPasswordRequestDto request) throws UserNotFoundException, InvalidCredentialsException {
        return ResponseEntity.ok(authService.resetPassword(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto request) throws Exception {
        var authUserDto = authService.authenticateUser(request);

        var authResponseDto = AuthenticationResponseDto.builder()
                .user(authUserDto.getUser())
                .accessJwtExpiresOn(authUserDto.getAccessJwtExpiresOn())
                .refreshJwtExpiresOn(authUserDto.getRefreshJwtExpiresOn())
                .build();

        var headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers", "Authorization, X-Refresh-Token");
        headers.add("Authorization", "Bearer " + authUserDto.getAccessJwt());
        headers.add("X-Refresh-Token", authUserDto.getRefreshJwt());

        return ResponseEntity.ok()
                .headers(headers)
                .body(authResponseDto);
    }

    @GetMapping("/refresh-access")
    public ResponseEntity<Boolean> refreshAccessToken(@RequestHeader("X-Refresh-Token") String refreshToken) throws UserNotFoundException {
        String newAccessJwt = authService.refreshAccessToken(refreshToken);

        var headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + newAccessJwt);

        return ResponseEntity.ok()
                .headers(headers)
                .body(true);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Boolean> forgotPassword(@RequestParam(name = "email") String userEmail) {
        return ResponseEntity.ok(authService.forgotPassword(userEmail));
    }
}
