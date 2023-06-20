package com.groupshow.artwork.writing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/writing")
@CrossOrigin(origins = "*")
public class WritingController {
	
	@Autowired
	private WritingService writingService;
	
	@PostMapping("upload")
	public ResponseEntity<Boolean> uploadWriting(@RequestBody WritingDto writingDto) {
		return ResponseEntity.ok(writingService.uploadWriting(writingDto));
	}
}
