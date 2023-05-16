package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.Photograph;
import com.groupshow.services.PhotographService;

@RestController
@RequestMapping("/photograph")
@CrossOrigin(origins = "http://localhost:3000")
public class PhotographController {
	
	@Autowired
	private PhotographService photographService;
	
	@PostMapping("/upload")
	public Photograph uploadPhotograph(Photograph photograph) {
		return photographService.uploadPhotograph(photograph);
	}
	
}
