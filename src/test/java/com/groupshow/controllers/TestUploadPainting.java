package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.Painting;
import com.groupshow.models.PaintingType;
import com.groupshow.models.User;
import com.groupshow.models.UserRole;
import com.groupshow.services.UserService;
import com.groupshow.utilities.dto.PaintingDto;

@SpringBootTest
public class TestUploadPainting {

    @Autowired
    private PaintingController paintingController;
    
    @Autowired
    private UserService userService;

    @Test
    public void testUploadPainting() {
        PaintingDto painting = new PaintingDto();
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
            
            painting.setArtistID(1);
            painting.setArtworkTitle("TestTitle");
            painting.setArtistStatement("TestStatement");
            painting.setArtworkURL("TestURL");
            painting.setPaintingType(PaintingType.ACRYLIC);
            painting.setPaintingWidth(45.8);
            painting.setPaintingHeight(80.1);


            Painting savedPainting = paintingController.uploadPainting(painting);


            assertNotNull(savedPainting.getArtworkID());
            assertEquals(painting.getArtworkTitle(), savedPainting.getTitle());
            assertNotNull(savedPainting.getTitle());
            assertEquals(painting.getArtistStatement(), savedPainting.getArtistStatement());
            assertEquals(painting.getArtworkURL(), savedPainting.getUrl());
            assertNotNull(savedPainting.getUrl());
            assertEquals(painting.getPaintingType(), savedPainting.getType());
            assertNotNull(savedPainting.getType());
            assertEquals(painting.getPaintingWidth(), savedPainting.getWidthInches());
            assertNotNull(savedPainting.getWidthInches());
            assertEquals(painting.getPaintingHeight(), savedPainting.getHeightInches());
            assertNotNull(savedPainting.getHeightInches());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
    }
}
