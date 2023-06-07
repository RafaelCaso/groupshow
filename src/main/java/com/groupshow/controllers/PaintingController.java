package com.groupshow.controllers;

import com.groupshow.models.Painting;
import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;
import com.groupshow.services.PaintingService;
import com.groupshow.utilities.dto.PaintingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/painting")
@CrossOrigin(origins = "http://localhost:3000")
public class PaintingController {

    @Autowired
    private PaintingService paintingService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public Painting uploadPainting(@RequestBody PaintingDto paintingDto) {
        User artist = userRepository.findById(paintingDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found."));

        Painting painting = new Painting();

        painting.setType(paintingDto.getPaintingType());
        painting.setWidthInches(paintingDto.getPaintingWidth());
        painting.setHeightInches(paintingDto.getPaintingHeight());
        painting.setTitle(paintingDto.getArtworkTitle());
        painting.setArtistStatement(paintingDto.getArtistStatement());
        painting.setArtist(artist);
        painting.setUrl(paintingDto.getArtworkURL());
        painting.setIsOpenForCritique(paintingDto.getOpenForCritique());

        return paintingService.uploadPainting(painting);
    }
}
