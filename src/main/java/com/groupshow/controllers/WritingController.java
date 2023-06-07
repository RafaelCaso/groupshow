package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.User;
import com.groupshow.models.Writing;
import com.groupshow.repositories.UserRepository;
import com.groupshow.services.WritingService;
import com.groupshow.utilities.dto.WritingDto;

@RestController
@RequestMapping("/writing")
@CrossOrigin(origins = "http://localhost:3000")
public class WritingController {
	
	@Autowired
	private WritingService writingService;
	
	@Autowired
    private UserRepository userRepository;
	
	@PostMapping("upload")
	public Writing uploadWriting(@RequestBody WritingDto writingDto) {
		
		User artist = userRepository.findById(writingDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found"));
		
		Writing writing = new Writing();
		
		writing.setArtist(artist);
		writing.setTitle(writingDto.getArtworkTitle());
		writing.setArtistStatement(writingDto.getArtistStatement());
		writing.setUrl(writingDto.getArtworkURL());
		writing.setType(writingDto.getWritingType());
		writing.setFiction(writingDto.getFiction());
		writing.setWordCount(writingDto.getWordCount());
		writing.setIsOpenForCritique(writingDto.getOpenForCritique());
		
		return writingService.uploadWriting(writing);
	}
	

}
