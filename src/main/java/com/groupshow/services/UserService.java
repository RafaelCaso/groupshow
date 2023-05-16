package com.groupshow.services;

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
		return userRepository.save(user);
	}
}
