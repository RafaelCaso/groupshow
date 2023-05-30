package com.groupshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.models.Song;
import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;
import com.groupshow.services.SongService;
import com.groupshow.utilities.dto.SongDto;

@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {

    @Autowired
    private SongService songService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public Song uploadSong(@RequestBody SongDto songDto) {
    	User artist = userRepository.findById(songDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found"));
    	
    	Song song = new Song();
    	
    	song.setArtist(artist);
    	song.setArtistStatement(songDto.getArtistStatement());
    	song.setArtworkURL(songDto.getArtworkURL());
    	song.setArtworkTitle(songDto.getArtworkTitle());
    	song.setDurationMin(songDto.getDurationMin());
    	song.setDurationSec(songDto.getDurationSec());
    	song.setOpenForCritique(songDto.getOpenForCritique());
    	
        return songService.uploadSong(song);
    }
}
