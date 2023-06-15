package com.groupshow.artwork;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.groupshow.artwork.song.Song;
import com.groupshow.artwork.song.SongController;
import com.groupshow.artwork.song.SongDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.user.User;
import com.groupshow.user.UserRole;

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
        user.setUserRole(UserRole.STUDENT);
        
        
        try {
        	userService.addUser(user);
            
            song.setArtworkTitle("TestSong");
            song.setArtistStatement("TestStatement");
            song.setArtworkURL("TestURL");
            song.setDurationMins(2);
            song.setDurationSecs(38);
            song.setArtistID(1);
            
            Song savedSong = songController.uploadSong(song);


            assertNotNull(savedSong.getArtworkID());
            assertEquals(song.getArtworkTitle(), savedSong.getTitle());
            assertNotNull(savedSong.getTitle());
            assertEquals(song.getArtistStatement(), savedSong.getStatement());
            assertEquals(song.getArtworkURL(), savedSong.getBucketUrl());
            assertNotNull(savedSong.getBucketUrl());
            assertEquals(song.getDurationMins(), savedSong.getDurationMins());
            assertNotNull(savedSong.getDurationMins());
            assertEquals(song.getDurationSecs(), savedSong.getDurationSecs());
            assertNotNull(savedSong.getDurationSecs());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
