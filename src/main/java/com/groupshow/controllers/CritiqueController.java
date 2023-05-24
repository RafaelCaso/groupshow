package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.Critique;
import com.groupshow.models.User;
import com.groupshow.repositories.PaintingRepository;
import com.groupshow.repositories.PerformanceRepository;
import com.groupshow.repositories.PhotographRepository;
import com.groupshow.repositories.SongRepository;
import com.groupshow.repositories.UserRepository;
import com.groupshow.repositories.VideoRepository;
import com.groupshow.repositories.WritingRepository;
import com.groupshow.services.CritiqueService;
import com.groupshow.utilities.dto.CritiqueDto;

@RestController
@RequestMapping("/critique")
@CrossOrigin(origins = "http://localhost:3000")
public class CritiqueController {

	@Autowired
	private CritiqueService critiqueService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired 
	private PerformanceRepository performanceRepository;
	
	@Autowired 
	private WritingRepository writingRepository;
	
	@Autowired
	private PhotographRepository photographRepository;
	
	@PostMapping("/add")
	public void addCritique(@RequestBody CritiqueDto critiqueDto) {
		
		User critic = userRepository.findById(critiqueDto.getUserID()).get();

		Critique critique = new Critique();
		critique.setContent(critiqueDto.getContent());
		
		if (paintingRepository.findById(critiqueDto.getArtworkID()).isPresent())
            critique.setArtwork(paintingRepository.findById(critiqueDto.getArtworkID()).get());
        else if (songRepository.findById(critiqueDto.getArtworkID()).isPresent())
        	critique.setArtwork(songRepository.findById(critiqueDto.getArtworkID()).get());
        else if (photographRepository.findById(critiqueDto.getArtworkID()).isPresent())
        	critique.setArtwork(photographRepository.findById(critiqueDto.getArtworkID()).get());
        else if (writingRepository.findById(critiqueDto.getArtworkID()).isPresent())
        	critique.setArtwork(writingRepository.findById(critiqueDto.getArtworkID()).get());
        else if (performanceRepository.findById(critiqueDto.getArtworkID()).isPresent())
        	critique.setArtwork(performanceRepository.findById(critiqueDto.getArtworkID()).get());
        else if (videoRepository.findById(critiqueDto.getArtworkID()).isPresent())
        	critique.setArtwork(videoRepository.findById(critiqueDto.getArtworkID()).get());
        else
            throw new RuntimeException("Artwork not found");
		
		critique.setCritic(critic);
		
		critiqueService.addCritique(critique);
	}
	
}
