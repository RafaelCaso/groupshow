package com.groupshow.services;

import com.groupshow.utilities.Registrar;
import com.groupshow.utilities.TokenGenerator;
import com.groupshow.utilities.dto.UserArtworkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;

import java.io.IOException;

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
			user.setIsRegTokenActivated(true);
			userRepository.save(user);
			return true;
		}

		return false;
	}

	public User loginUser(String email, String password) {
		User user = userRepository.findByEmail(email);

		if (user.getPassword() != password) {
			throw new RuntimeException("Invalid credentials.");
		}

		return user;
	}

	public UserArtworkDto retrieveAllSubmittedArtwork(int userID) {
		User user = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));
		UserArtworkDto userArtworkDto = new UserArtworkDto();

		userArtworkDto.setPaintings(user.getPaintings());
		userArtworkDto.setPerformances(user.getPerformances());
		userArtworkDto.setPhotographs(user.getPhotographs());
		userArtworkDto.setSongs(user.getSongs());
		userArtworkDto.setVideos(user.getVideos());
		userArtworkDto.setWritings(user.getWritings());

		return userArtworkDto;
	}
}
