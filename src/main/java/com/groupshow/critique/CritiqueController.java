package com.groupshow.critique;

import com.groupshow.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.groupshow.user.User;
import com.groupshow.artwork.painting.PaintingRepository;
import com.groupshow.artwork.performance.PerformanceRepository;
import com.groupshow.artwork.photograph.PhotographRepository;
import com.groupshow.artwork.song.SongRepository;
import com.groupshow.user.UserRepository;
import com.groupshow.artwork.video.VideoRepository;
import com.groupshow.artwork.writing.WritingRepository;

import java.util.List;

@RestController
@RequestMapping("/critique")
@CrossOrigin(origins = "*")
public class CritiqueController {

	@Autowired
	private CritiqueService critiqueService;
	
	@PostMapping("/add")
	public ResponseEntity<Boolean> addCritique(@RequestBody CritiqueDto critiqueDto) throws UserNotFoundException  {
		return ResponseEntity.ok(critiqueService.addCritique(critiqueDto));
	}

	@GetMapping("/all/{artworkID}")
	public ResponseEntity<List<Critique>> getCritiquesByArtworkID(@PathVariable Integer artworkID) {
		return ResponseEntity.ok(critiqueService.getCritiquesByArtworkID(artworkID));
	}
}
