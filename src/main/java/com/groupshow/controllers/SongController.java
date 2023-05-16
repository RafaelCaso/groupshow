package com.groupshow.controllers;

import com.groupshow.models.Song;
import com.groupshow.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/upload")
    public Song uploadSong(Song song) {
        return songService.uploadSong(song);
    }
}
