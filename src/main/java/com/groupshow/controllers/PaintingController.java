package com.groupshow.controllers;

import com.groupshow.models.Painting;
import com.groupshow.services.PaintingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/painting")
@CrossOrigin(origins = "http://localhost:3000")
public class PaintingController {

    @Autowired
    private PaintingService paintingService;

    @PostMapping("/upload")
    public Painting uploadPainting(Painting painting) {
        return paintingService.uploadPainting(painting);
    }
}
