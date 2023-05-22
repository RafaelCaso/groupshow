package com.groupshow.services;

import com.groupshow.utilities.TokenGenerator;
import com.groupshow.utilities.dto.UserArtworkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		String regTokenID = TokenGenerator.createNewToken();
		user.setRegTokenID(regTokenID);
		user.setIsRegTokenActivated(false);

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
