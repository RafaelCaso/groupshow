package com.groupshow.controllers;

import com.groupshow.models.Artwork;
import com.groupshow.services.NewsfeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/newsfeed")
@CrossOrigin(origins="*")
public class NewsfeedController {

    @Autowired
    private NewsfeedService newsfeedService;

    @GetMapping("/")
    public List<Artwork> populateNewsfeed() {
        return newsfeedService.retrieveTwentyMostRecentArtworks();
    }
}
