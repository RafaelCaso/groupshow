package com.groupshow.controllers;

import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;
import com.groupshow.utilities.dto.PhotographDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.groupshow.models.Photograph;
import com.groupshow.services.PhotographService;

@RestController
@RequestMapping("/photograph")
@CrossOrigin(origins = "http://localhost:3000")
public class PhotographController {
	
	@Autowired
	private PhotographService photographService;

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/upload")
	public Photograph uploadPhotograph(@RequestBody PhotographDto photographDto) {
		User artist = userRepository.findById(photographDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found."));

		Photograph photograph = new Photograph();

		photograph.setIsPrint(photographDto.getIsPrint());
		photograph.setWidthInches(photographDto.getWidthInches());
		photograph.setHeightInches(photographDto.getHeightInches());
		photograph.setTitle(photographDto.getTitle());
		photograph.setStatement(photographDto.getStatement());
		photograph.setArtist(artist);
		photograph.setUrl(photographDto.getUrl());
		photograph.setIsOpenForCritique(photographDto.getIsOpenForCritique());

		return photographService.uploadPhotograph(photograph);
	}
	
}
