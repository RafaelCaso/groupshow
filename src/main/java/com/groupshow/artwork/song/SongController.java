package com.groupshow.artwork.song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;

@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "*")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadSong(@RequestBody SongDto songDto) {
		return ResponseEntity.ok(songService.uploadSong(songDto));
    }
}
