package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.Painting;
import com.groupshow.models.PaintingType;
import com.groupshow.utilities.dto.PaintingDto;

@SpringBootTest
public class TestUploadPainting {

    @Autowired
    private PaintingController paintingController;

    @Test
    public void testUploadPainting() {
        PaintingDto painting = new PaintingDto();

        painting.setArtworkTitle("TestTitle");
        painting.setArtistStatement("TestStatement");
        painting.setArtworkURL("TestURL");
        painting.setPaintingType(PaintingType.ACRYLIC);
        painting.setPaintingWidth(45.8);
        painting.setPaintingHeight(80.1);

        Painting savedPainting = paintingController.uploadPainting(painting);


        assertNotNull(savedPainting.getPaintingID());
        assertEquals(painting.getArtworkTitle(), savedPainting.getArtworkTitle());
        assertNotNull(savedPainting.getArtworkTitle());
        assertEquals(painting.getArtistStatement(), savedPainting.getArtistStatement());
        assertEquals(painting.getArtworkURL(), savedPainting.getArtworkURL());
        assertNotNull(savedPainting.getArtworkURL());
        assertEquals(painting.getPaintingType(), savedPainting.getPaintingType());
        assertNotNull(savedPainting.getPaintingType());
        assertEquals(painting.getPaintingWidth(), savedPainting.getPaintingWidth());
        assertNotNull(savedPainting.getPaintingWidth());
        assertEquals(painting.getPaintingHeight(), savedPainting.getPaintingHeight());
        assertNotNull(savedPainting.getPaintingHeight());
    }
}
