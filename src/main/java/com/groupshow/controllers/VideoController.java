package com.groupshow.controllers;

import com.groupshow.models.Video;
import com.groupshow.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public Video uploadVideo(Video video) {
        return videoService.uploadVideo(video);
    }
}
