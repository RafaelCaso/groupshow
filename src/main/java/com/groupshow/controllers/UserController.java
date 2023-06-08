package com.groupshow.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.Artwork;
import com.groupshow.services.AuthenticationService;
import com.groupshow.services.UserService;
import com.groupshow.utilities.dto.AuthenticationRequestDto;
import com.groupshow.utilities.dto.AuthenticationResponseDto;
import com.groupshow.utilities.dto.PasswordResetDto;
import com.groupshow.utilities.dto.RegisterRequestDto;


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
	
	@PostMapping("/reset-password")
	public Boolean resetPassword(@RequestParam(name="userID")Integer userID, @RequestBody PasswordResetDto passwordresetDto)  {
		
		if(userService.resetPassword(userID, passwordresetDto.getPassword(), passwordresetDto.getPasswordConfirmation())) {
			return true;
		}
		
		return false;
		
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}

	@GetMapping("/{userID}/submitted-artwork")
	public List<Artwork> retrieveAllSubmittedArtwork(@PathVariable int userID) {
		return userService.retrieveAllSubmittedArtwork(userID);
	}
}


