package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.User;
import com.groupshow.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User addUser(User user) {
		return userService.addUser(user);
	}
	
}


