package com.groupshow.artwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artwork")
@CrossOrigin(origins="*")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping("/get-twenty")
    public ResponseEntity<List<Artwork>> getTwentyMostRecentArtworks() {
        return ResponseEntity.ok(artworkService.getTwentyMostRecentArtworks());
    }

    @GetMapping("?artwork={artworkID}")
    public ResponseEntity<Artwork> getArtworkByID(@PathVariable Integer artworkID) {
        return ResponseEntity.ok(artworkService.getArtworkByID(artworkID));
    }

    @GetMapping("?user={userID}/all")
    public ResponseEntity<List<Artwork>> getAllArtworkByUser(@PathVariable Integer userID) {
        return ResponseEntity.ok(artworkService.getAllUserArtwork(userID));
    }

    @GetMapping("?user={userID}/{artworkType}/all")
    public ResponseEntity<List<? extends Artwork>> getAllArtworkByType(@PathVariable Integer userID, @PathVariable String artworkType) {
        switch (artworkType) {
            case "paintings":
                return ResponseEntity.ok(artworkService.getAllUserPaintings(userID));
            case "performances":
                return ResponseEntity.ok(artworkService.getAllUserPerformances(userID));
            case "photographs":
                return ResponseEntity.ok(artworkService.getAllUserPhotographs(userID));
            case "songs":
                return ResponseEntity.ok(artworkService.getAllUserSongs(userID));
            case "videos":
                return ResponseEntity.ok(artworkService.getAllUserVideos(userID));
            case "writings":
                return ResponseEntity.ok(artworkService.getAllUserWritings(userID));
            default:
                throw new RuntimeException("The given artwork type is not valid.");
        }
    }
}
