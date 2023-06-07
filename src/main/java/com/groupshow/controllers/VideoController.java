package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.User;
import com.groupshow.models.Video;
import com.groupshow.repositories.UserRepository;
import com.groupshow.services.VideoService;
import com.groupshow.utilities.dto.VideoDto;

@RestController
@RequestMapping("/video")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    @Autowired
    private VideoService videoService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public Video uploadVideo(@RequestBody VideoDto videoDto) {
    	
    	User artist = userRepository.findById(videoDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found"));
    	
    	Video video = new Video();
    	
    	video.setArtist(artist);
    	video.setArtistStatement(videoDto.getArtistStatement());
    	video.setTitle(videoDto.getArtworkTitle());
    	video.setUrl(videoDto.getArtworkURL());
    	video.setType(videoDto.getVideoType());
    	video.setDurationHour(videoDto.getDurationHour());
    	video.setDurationMin(videoDto.getDurationMin());
    	video.setDurationSec(videoDto.getDurationSec());
		video.setIsOpenForCritique(videoDto.getOpenForCritique());
    	
        return videoService.uploadVideo(video);
    }
}
