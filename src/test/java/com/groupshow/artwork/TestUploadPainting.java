package com.groupshow.artwork;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.groupshow.artwork.painting.Painting;
import com.groupshow.artwork.painting.PaintingController;
import com.groupshow.artwork.painting.PaintingDto;
import com.groupshow.artwork.painting.PaintingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.user.User;
import com.groupshow.user.UserRole;

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
        user.setUserRole(UserRole.STUDENT);

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
            assertEquals(painting.getArtistStatement(), savedPainting.getStatement());
            assertEquals(painting.getArtworkURL(), savedPainting.getBucketUrl());
            assertNotNull(savedPainting.getBucketUrl());
            assertEquals(painting.getPaintingType(), savedPainting.getType());
            assertNotNull(savedPainting.getType());
            assertEquals(painting.getPaintingWidth(), savedPainting.getWidth());
            assertNotNull(savedPainting.getWidth());
            assertEquals(painting.getPaintingHeight(), savedPainting.getHeight());
            assertNotNull(savedPainting.getHeight());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
    }
}
