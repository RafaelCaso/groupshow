package com.groupshow.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupshow.models.Artwork;
import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;
import com.groupshow.utilities.Registrar;
import com.groupshow.utilities.TokenGenerator;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) throws IOException {
		String regTokenID = TokenGenerator.createNewToken();
		user.setRegTokenID(regTokenID);
		user.setIsRegTokenActivated(false);

		userRepository.save(user);

		User savedUser = retrieveUser(user.getUserID());
		Registrar.sendEmail(savedUser);
		
		return savedUser;
	}

	public User retrieveUser(Integer userID) {
		return userRepository.findById(userID).get();
	}

	public Boolean activateUser(String regTokenID) {
		User user = userRepository.findByRegTokenID(regTokenID);

		if (user != null) {
			user.isEnabled();
			userRepository.save(user);
			return true;
		}

		return false;
	}
	
	public Boolean resetPassword(Integer userID, String password, String passwordConfirmation) {
		
		if(password.equals(passwordConfirmation)) {
			User user = retrieveUser(userID);
			user.setPassword(passwordConfirmation);
			userRepository.save(user);
			return true;
		}
		
		return false;
	}

	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		if (user.getPassword() != password) {
			throw new RuntimeException("Invalid credentials.");
		}

		return user;
	}

	public List<Artwork> retrieveAllSubmittedArtwork(int userID) {
		User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

		List<Artwork> artworkList = new ArrayList<>();
		
		artworkList.addAll(user.getPaintings());
		artworkList.addAll(user.getSongs());
		artworkList.addAll(user.getPerformances());
		artworkList.addAll(user.getVideos());
		artworkList.addAll(user.getWritings());
		artworkList.addAll(user.getPhotographs());
		
		return artworkList;
	}
}
