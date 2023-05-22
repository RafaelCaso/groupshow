package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.groupshow.models.Writing;
import com.groupshow.services.WritingService;

@RestController
@RequestMapping("/writing")
@CrossOrigin(origins = "http://localhost:3000")
public class WritingController {
	
	@Autowired
	private WritingService writingService;
	
	@PostMapping("upload")
	public Writing uploadWriting(@RequestBody Writing writing) {
		return writingService.uploadWriting(writing);
	}
	

}
