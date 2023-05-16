package com.groupshow.controllers;

import com.groupshow.models.Song;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestUploadSong {

    @Autowired
    private SongController songController;

    @Test
    public void testUploadSong() {
        Song song = new Song();

        song.setArtworkTitle("TestSong");
        song.setArtistStatement("TestStatement");
        song.setArtworkURL("TestURL");
        song.setDurationMin(2);
        song.setDurationSec(38);

        Song savedSong = songController.uploadSong(song);


        assertNotNull(savedSong.getSongID());
        assertEquals(song.getArtworkTitle(), savedSong.getArtworkTitle());
        assertNotNull(savedSong.getArtworkTitle());
        assertEquals(song.getArtistStatement(), savedSong.getArtistStatement());
        assertEquals(song.getArtworkURL(), savedSong.getArtworkURL());
        assertNotNull(savedSong.getArtworkURL());
        assertEquals(song.getDurationMin(), savedSong.getDurationMin());
        assertNotNull(savedSong.getDurationMin());
        assertEquals(song.getDurationSec(), savedSong.getDurationSec());
        assertNotNull(savedSong.getDurationSec());
    }
}
