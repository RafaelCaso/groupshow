package com.groupshow.artwork;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.groupshow.artwork.writing.Writing;
import com.groupshow.artwork.writing.WritingController;
import com.groupshow.artwork.writing.WritingDto;
import com.groupshow.artwork.writing.WritingType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.user.User;
import com.groupshow.user.UserRole;

@SpringBootTest
public class TestUploadWriting {

	@Autowired
	private WritingController writingController;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testUploadWriting() {
		WritingDto writing = new WritingDto();
		
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
    		
    		writing.setArtworkTitle("Test Title");
    		writing.setArtistStatement("Test statement");
    		writing.setArtworkURL("testURL");
    		writing.setFiction(false);
    		writing.setWordCount(1000);
    		writing.setWritingType(WritingType.POEM);
    		writing.setArtistID(1);
    		
    		Writing savedWriting = writingController.uploadWriting(writing);
    		
    		assertNotNull(savedWriting.getArtworkID());
    		assertNotNull(savedWriting.getTitle());
    		assertNotNull(savedWriting.getBucketUrl());
    		assertNotNull(savedWriting.isFiction());
    		assertNotNull(savedWriting.getWordCount());
    		assertNotNull(savedWriting.getType());
    		assertEquals(savedWriting.getTitle(), writing.getArtworkTitle());
    		assertEquals(savedWriting.getStatement(), writing.getArtistStatement());
    		assertEquals(savedWriting.getBucketUrl(), writing.getArtworkURL());
    		assertEquals(savedWriting.isFiction(), writing.getFiction());
    		assertEquals(savedWriting.getWordCount(), writing.getWordCount());
    		assertEquals(savedWriting.getType(), writing.getWritingType());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
}
