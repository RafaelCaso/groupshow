package com.groupshow.controllers;

import com.groupshow.models.Video;
import com.groupshow.models.VideoType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestUploadVideo {

    @Autowired
    private VideoController videoController;

    @Test
    public void testUploadVideo() {
        Video video = new Video();

        video.setArtworkTitle("TestTitle");
        video.setArtistStatement("TestStatement");
        video.setArtworkURL("TestURL");
        video.setVideoType(VideoType.EXPERIMENTAL);
        video.setDurationHour(0);
        video.setDurationMin(29);
        video.setDurationSec(14);
        
        Video savedVideo = videoController.uploadVideo(video);
        
        
        assertNotNull(savedVideo.getVideoID());
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
    }
}
