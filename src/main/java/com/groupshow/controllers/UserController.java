package com.groupshow.controllers;

import com.groupshow.services.AuthenticationService;
import com.groupshow.utilities.dto.AuthenticationRequestDto;
import com.groupshow.utilities.dto.AuthenticationResponseDto;
import com.groupshow.utilities.dto.RegisterRequestDto;
import com.groupshow.utilities.dto.UserArtworkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.groupshow.services.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterRequestDto request) {
		return ResponseEntity.ok(authService.register(request));
	}

	@GetMapping("/activate")
	public Boolean activateUser(@RequestParam(name="regTokenID")String regTokenID) {
		return userService.activateUser(regTokenID);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

	@GetMapping("/{userID}/submitted-artwork")
	public UserArtworkDto retrieveAllSubmittedArtwork(@PathVariable int userID) {
		return userService.retrieveAllSubmittedArtwork(userID);
	}
}


