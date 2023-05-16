package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.Writing;
import com.groupshow.services.WritingService;

@RestController
@RequestMapping("/writing")
@CrossOrigin(origins = "http://localhost:3000")
public class WritingController {
	
	@Autowired
	private WritingService writingService;
	
	@PostMapping("upload")
	public Writing uploadWriting(Writing writing) {
		return writingService.uploadWriting(writing);
	}
	

}
