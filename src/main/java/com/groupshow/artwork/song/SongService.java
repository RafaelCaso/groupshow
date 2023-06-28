package com.groupshow.artwork.song;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean uploadSong(SongDto songDto) {
        User artist = userRepository.findById(songDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found"));
        var song = new Song();

        song.setTitle(songDto.getTitle());
        song.setArtworkType(songDto.getArtworkType());
        song.setArtistStatement(songDto.getArtistStatement());
        song.setArtist(artist);
        song.setBucketUrl(songDto.getBucketUrl());
        song.setIsOpenForCritique(songDto.getIsOpenForCritique());
        song.setDurationMins(songDto.getDurationMins());
        song.setDurationSecs(songDto.getDurationSecs());

        songRepository.save(song);
        return true;
    }
    
}
