package com.groupshow.controllers;

import com.groupshow.models.Video;
import com.groupshow.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public Video uploadVideo(@RequestBody Video video) {
        return videoService.uploadVideo(video);
    }
}
