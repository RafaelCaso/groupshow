package com.groupshow.artwork.photograph;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photograph")
@CrossOrigin(origins = "*")
public class PhotographController {
	
	@Autowired
	private PhotographService photographService;
	
	@PostMapping("/upload")
	public ResponseEntity<Boolean> uploadPhotograph(@RequestBody PhotographDto photographDto) {
		return ResponseEntity.ok(photographService.uploadPhotograph(photographDto));
	}
}
