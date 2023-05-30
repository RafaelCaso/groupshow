package com.groupshow.controllers;

import com.groupshow.utilities.dto.UserArtworkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.groupshow.models.User;
import com.groupshow.services.UserService;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User addUser(@RequestBody User user) throws IOException {
		return userService.addUser(user);
	}

	@GetMapping("/activate")
	public Boolean activateUser(@RequestParam(name="regTokenID")String regTokenID) {
		return userService.activateUser(regTokenID);
	}

	@GetMapping("/{userID}/submitted-artwork")
	public UserArtworkDto retrieveAllSubmittedArtwork(@PathVariable int userID) {
		return userService.retrieveAllSubmittedArtwork(userID);
	}
	
}


