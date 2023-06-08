package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.User;
import com.groupshow.models.UserRole;
import com.groupshow.models.Video;
import com.groupshow.models.VideoGenre;
import com.groupshow.services.UserService;
import com.groupshow.utilities.dto.VideoDto;

@SpringBootTest
public class TestUploadVideo {

    @Autowired
    private VideoController videoController;
    
    @Autowired
    private UserService userService;

    @Test
    public void testUploadVideo() {
        VideoDto video = new VideoDto();
        
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

            video.setArtworkTitle("TestTitle");
            video.setArtistStatement("TestStatement");
            video.setArtworkURL("TestURL");
            video.setGenre(VideoGenre.EXPERIMENTAL);
            video.setDurationHour(0);
            video.setDurationMin(29);
            video.setDurationSec(14);
            video.setArtistID(1);
            
            Video savedVideo = videoController.uploadVideo(video);
            
            
            assertNotNull(savedVideo.getArtworkID());
            assertEquals(video.getArtworkTitle(), savedVideo.getTitle());
            assertNotNull(savedVideo.getTitle());
            assertEquals(video.getArtistStatement(), savedVideo.getStatement());
            assertEquals(video.getArtworkURL(), savedVideo.getUrl());
            assertNotNull(savedVideo.getUrl());
            assertEquals(video.getGenre(), savedVideo.getGenre());
            assertNotNull(savedVideo.getGenre());
            assertEquals(video.getDurationHour(), savedVideo.getDurationHour());
            assertNotNull(savedVideo.getDurationHour());
            assertEquals(video.getDurationSec(), savedVideo.getDurationSec());
            assertNotNull(savedVideo.getDurationSec());
            assertEquals(video.getDurationSec(), savedVideo.getDurationSec());
            assertNotNull(savedVideo.getDurationSec());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
