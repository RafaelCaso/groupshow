package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.Writing;
import com.groupshow.models.WritingType;
import com.groupshow.utilities.dto.WritingDto;

@SpringBootTest
public class TestUploadWriting {

	@Autowired
	private WritingController writingController;
	
	@Test
	public void testUploadWriting() {
		WritingDto writing = new WritingDto();
		
		writing.setArtworkTitle("Test Title");
		writing.setArtistStatement("Test statement");
		writing.setArtworkURL("testURL");
		writing.setFiction(false);
		writing.setWordCount(1000);
		writing.setWritingType(WritingType.POEM);
		
		Writing savedWriting = writingController.uploadWriting(writing);
		
		assertNotNull(savedWriting.getWritingID());
		assertNotNull(savedWriting.getArtworkTitle());
		assertNotNull(savedWriting.getArtworkURL());
		assertNotNull(savedWriting.isFiction());
		assertNotNull(savedWriting.getWordCount());
		assertNotNull(savedWriting.getWritingType());
		assertEquals(savedWriting.getArtworkTitle(), writing.getArtworkTitle());
		assertEquals(savedWriting.getArtistStatement(), writing.getArtistStatement());
		assertEquals(savedWriting.getArtworkURL(), writing.getArtworkURL());
		assertEquals(savedWriting.isFiction(), writing.getFiction());
		assertEquals(savedWriting.getWordCount(), writing.getWordCount());
		assertEquals(savedWriting.getWritingType(), writing.getWritingType());
	}
	
}
