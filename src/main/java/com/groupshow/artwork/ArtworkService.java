package com.groupshow.artwork;

import com.groupshow.artwork.painting.Painting;
import com.groupshow.artwork.performance.Performance;
import com.groupshow.artwork.photograph.Photograph;
import com.groupshow.artwork.song.Song;
import com.groupshow.artwork.video.Video;
import com.groupshow.artwork.writing.Writing;
import com.groupshow.security.JwtService;
import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArtworkService {
    private final ArtworkRepository artworkRepository;
    private final UserRepository userRepository;

    public List<Artwork> getTwentyMostRecentArtworks() {
        return artworkRepository.findTop20ByOrderBySubmissionDateDesc();
    }

    public List<Artwork> getAllArtworkByUserID(Integer userID) {
        User thisUser = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

        List<Artwork> artworks = new ArrayList<>();

        artworks.addAll(thisUser.getPaintings());
        artworks.addAll(thisUser.getPerformances());
        artworks.addAll(thisUser.getPhotographs());
        artworks.addAll(thisUser.getSongs());
        artworks.addAll(thisUser.getVideos());
        artworks.addAll(thisUser.getWritings());

        return artworks;
    }

    public List<Painting> getAllUserPaintings(Integer userID) {
        User thisUser = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

        List<Painting> paintings = new ArrayList<>();
        paintings.addAll(thisUser.getPaintings());

        return paintings;
    }

    public List<Performance> getAllUserPerformances(Integer userID) {
        User thisUser = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

        List<Performance> performances = new ArrayList<>();
        performances.addAll(thisUser.getPerformances());

        return performances;
    }

    public List<Photograph> getAllUserPhotographs(Integer userID) {
        User thisUser = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

        List<Photograph> photographs = new ArrayList<>();
        photographs.addAll(thisUser.getPhotographs());

        return photographs;
    }

    public List<Song> getAllUserSongs(Integer userID) {
        User thisUser = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

        List<Song> songs = new ArrayList<>();
        songs.addAll(thisUser.getSongs());

        return songs;
    }

    public List<Video> getAllUserVideos(Integer userID) {
        User thisUser = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

        List<Video> videos = new ArrayList<>();
        videos.addAll(thisUser.getVideos());

        return videos;
    }

    public List<Writing> getAllUserWritings(Integer userID) {
        User thisUser = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found."));

        List<Writing> writings = new ArrayList<>();
        writings.addAll(thisUser.getWritings());

        return writings;
    }

    public Artwork getSingleArtworkByID(Integer artworkID) {
        return artworkRepository.findById(artworkID).orElseThrow(() -> new RuntimeException("Artwork not found."));
    }

    public Boolean setCritiqueStatus(Integer artworkID, String critiqueStatus) {
        Artwork thisArtwork = artworkRepository.findById(artworkID).orElseThrow(() -> new RuntimeException("Artwork not found."));

        if (critiqueStatus.equals("open") && thisArtwork.getIsOpenForCritique() == false) {
            thisArtwork.setIsOpenForCritique(true);
            artworkRepository.save(thisArtwork);
            return true;
        } else if (critiqueStatus.equals("closed") && thisArtwork.getIsOpenForCritique() == true) {
            thisArtwork.setIsOpenForCritique(false);
            artworkRepository.save(thisArtwork);
            return true;
        }
        return false;
    }
}
