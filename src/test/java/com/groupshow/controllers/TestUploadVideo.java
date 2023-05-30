package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.User;
import com.groupshow.models.UserType;
import com.groupshow.models.Video;
import com.groupshow.models.VideoType;
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
        user.setUserType(UserType.STUDENT);
        
        try {
        	userService.addUser(user);

            video.setArtworkTitle("TestTitle");
            video.setArtistStatement("TestStatement");
            video.setArtworkURL("TestURL");
            video.setVideoType(VideoType.EXPERIMENTAL);
            video.setDurationHour(0);
            video.setDurationMin(29);
            video.setDurationSec(14);
            video.setArtistID(1);
            
            Video savedVideo = videoController.uploadVideo(video);
            
            
            assertNotNull(savedVideo.getArtworkID());
            assertEquals(video.getArtworkTitle(), savedVideo.getArtworkTitle());
            assertNotNull(savedVideo.getArtworkTitle());
            assertEquals(video.getArtistStatement(), savedVideo.getArtistStatement());
            assertEquals(video.getArtworkURL(), savedVideo.getArtworkURL());
            assertNotNull(savedVideo.getArtworkURL());
            assertEquals(video.getVideoType(), savedVideo.getVideoType());
            assertNotNull(savedVideo.getVideoType());
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
