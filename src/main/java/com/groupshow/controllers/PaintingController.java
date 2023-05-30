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

        painting.setPaintingType(paintingDto.getPaintingType());
        painting.setPaintingWidth(paintingDto.getPaintingWidth());
        painting.setPaintingHeight(paintingDto.getPaintingHeight());
        painting.setArtworkTitle(paintingDto.getArtworkTitle());
        painting.setArtistStatement(paintingDto.getArtistStatement());
        painting.setArtist(artist);
        painting.setArtworkURL(paintingDto.getArtworkURL());
        painting.setOpenForCritique(paintingDto.getOpenForCritique());

        return paintingService.uploadPainting(painting);
    }
}
