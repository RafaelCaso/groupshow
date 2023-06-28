package com.groupshow.artwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artwork")
@CrossOrigin(origins = "http://127.0.0.1:5173")

public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping("/get-twenty")
    public ResponseEntity<List<Artwork>> getTwentyMostRecentArtworks() {
        return ResponseEntity.ok()
                .body(artworkService.getTwentyMostRecentArtworks());
    }

    @GetMapping("/{artworkID}")
    public ResponseEntity<Artwork> getSingleArtworkByID(@PathVariable Integer artworkID) {
        return ResponseEntity.ok(artworkService.getSingleArtworkByID(artworkID));
    }
    
    
    @GetMapping("/all/{userID}")
    public ResponseEntity<List<Artwork>> getAllArtworkByUserID(@PathVariable Integer userID) {
        return ResponseEntity.ok(artworkService.getAllArtworkByUserID(userID));
    }

    @GetMapping("/all/{userID}/{artworkType}")
    public ResponseEntity<List<? extends Artwork>> getAllArtworkByType(@PathVariable Integer userID, @PathVariable String artworkType) {
        return switch (artworkType) {
            case "painting" -> ResponseEntity.ok(artworkService.getAllUserPaintings(userID));
            case "performance" -> ResponseEntity.ok(artworkService.getAllUserPerformances(userID));
            case "photograph" -> ResponseEntity.ok(artworkService.getAllUserPhotographs(userID));
            case "song" -> ResponseEntity.ok(artworkService.getAllUserSongs(userID));
            case "video" -> ResponseEntity.ok(artworkService.getAllUserVideos(userID));
            case "writing" -> ResponseEntity.ok(artworkService.getAllUserWritings(userID));
            default -> throw new RuntimeException("The given artwork type is not valid.");
        };
    }

    @GetMapping("/set-status/{artworkID}/{critiqueStatus}")
    public ResponseEntity<Boolean> setCritiqueStatus(@PathVariable Integer artworkID, @PathVariable String critiqueStatus) {
        return ResponseEntity.ok(artworkService.setCritiqueStatus(artworkID, critiqueStatus));
    }
}
