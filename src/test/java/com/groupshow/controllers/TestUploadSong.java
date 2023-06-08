package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.Song;
import com.groupshow.models.User;
import com.groupshow.models.UserRole;
import com.groupshow.services.UserService;
import com.groupshow.utilities.dto.SongDto;

@SpringBootTest
public class TestUploadSong {

    @Autowired
    private SongController songController;
    
    @Autowired
    private UserService userService;

    @Test
    public void testUploadSong() {
        SongDto song = new SongDto();

        User user = new User();
        
        user.setUserID(1);
        user.setEmail("adgadgqg");
        user.setFirstName("Asgasg");
        user.setLastName("asdgadsg");
        user.setGradeLevel("qaegag");
        user.setMajor("asfg");
        user.setMinor("asgasg");
        user.setRole(UserRole.STUDENT);
        
        
        try {
        	userService.addUser(user);
            
            song.setArtworkTitle("TestSong");
            song.setArtistStatement("TestStatement");
            song.setArtworkURL("TestURL");
            song.setDurationMin(2);
            song.setDurationSec(38);
            song.setArtistID(1);
            
            Song savedSong = songController.uploadSong(song);


            assertNotNull(savedSong.getArtworkID());
            assertEquals(song.getArtworkTitle(), savedSong.getTitle());
            assertNotNull(savedSong.getTitle());
            assertEquals(song.getArtistStatement(), savedSong.getStatement());
            assertEquals(song.getArtworkURL(), savedSong.getUrl());
            assertNotNull(savedSong.getUrl());
            assertEquals(song.getDurationMin(), savedSong.getDurationMin());
            assertNotNull(savedSong.getDurationMin());
            assertEquals(song.getDurationSec(), savedSong.getDurationSec());
            assertNotNull(savedSong.getDurationSec());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
