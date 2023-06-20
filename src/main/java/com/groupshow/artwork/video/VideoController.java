package com.groupshow.artwork.video;

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
@RequestMapping("/video")
@CrossOrigin(origins = "*")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadVideo(@RequestBody VideoDto videoDto) {
		return ResponseEntity.ok(videoService.uploadVideo(videoDto));
    }
}
