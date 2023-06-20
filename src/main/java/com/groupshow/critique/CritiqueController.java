package com.groupshow.critique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.user.User;
import com.groupshow.artwork.painting.PaintingRepository;
import com.groupshow.artwork.performance.PerformanceRepository;
import com.groupshow.artwork.photograph.PhotographRepository;
import com.groupshow.artwork.song.SongRepository;
import com.groupshow.user.UserRepository;
import com.groupshow.artwork.video.VideoRepository;
import com.groupshow.artwork.writing.WritingRepository;

@RestController
@RequestMapping("/critique")
@CrossOrigin(origins = "*")
public class CritiqueController {

	@Autowired
	private CritiqueService critiqueService;
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> addCritique(@RequestBody CritiqueDto critiqueDto) {
		return ResponseEntity.ok(critiqueService.addCritique(critiqueDto));
	}
}
