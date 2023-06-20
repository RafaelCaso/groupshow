package com.groupshow.artwork;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.groupshow.artwork.video.Video;
import com.groupshow.artwork.video.VideoController;
import com.groupshow.artwork.video.VideoDto;
import com.groupshow.artwork.video.VideoType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.user.User;
import com.groupshow.user.UserRole;

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
        user.setUserRole(UserRole.STUDENT);
        
        try {
        	userService.addUser(user);

            video.setArtworkTitle("TestTitle");
            video.setArtistStatement("TestStatement");
            video.setArtworkURL("TestURL");
            video.setType(VideoType.EXPERIMENTAL);
            video.setDurationHours(0);
            video.setDurationMins(29);
            video.setDurationSecs(14);
            video.setArtistID(1);
            
            Video savedVideo = videoController.uploadVideo(video);
            
            
            assertNotNull(savedVideo.getArtworkID());
            assertEquals(video.getArtworkTitle(), savedVideo.getTitle());
            assertNotNull(savedVideo.getTitle());
            assertEquals(video.getArtistStatement(), savedVideo.getStatement());
            assertEquals(video.getArtworkURL(), savedVideo.getBucketUrl());
            assertNotNull(savedVideo.getBucketUrl());
            assertEquals(video.getType(), savedVideo.getArtworkType());
            assertNotNull(savedVideo.getArtworkType());
            assertEquals(video.getDurationHours(), savedVideo.getDurationHours());
            assertNotNull(savedVideo.getDurationHours());
            assertEquals(video.getDurationSecs(), savedVideo.getDurationSecs());
            assertNotNull(savedVideo.getDurationSecs());
            assertEquals(video.getDurationSecs(), savedVideo.getDurationSecs());
            assertNotNull(savedVideo.getDurationSecs());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
