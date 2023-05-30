package com.groupshow.services;

import com.groupshow.models.Song;
import com.groupshow.repositories.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song uploadSong(Song song) {
        return songRepository.save(song);
    }
    
    public Song retrieveSong(Integer songID) {
    	return songRepository.findById(songID).get();
    }
}
