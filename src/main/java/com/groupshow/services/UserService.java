package com.groupshow.services;

import com.groupshow.utilities.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		String regTokenID = TokenGenerator.createNewToken();
		user.setRegTokenID(regTokenID);

		return userRepository.save(user);
	}

	public Boolean activateUser(String regTokenID) {
		User user = userRepository.findByRegTokenID(regTokenID);

		if (user != null) {
			user.setIsRegTokenActivated(true);
			userRepository.save(user);
			return true;
		}

		return false;
	}
}
