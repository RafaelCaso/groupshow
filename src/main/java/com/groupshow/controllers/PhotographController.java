package com.groupshow.controllers;

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
	
	@PostMapping("/upload")
	public Photograph uploadPhotograph(@RequestBody Photograph photograph) {
		return photographService.uploadPhotograph(photograph);
	}
	
}
