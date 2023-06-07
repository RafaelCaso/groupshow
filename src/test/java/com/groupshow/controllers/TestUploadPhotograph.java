package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.groupshow.utilities.dto.PhotographDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.groupshow.models.Photograph;

@SpringBootTest
public class TestUploadPhotograph {

	@Autowired
	private PhotographController photographController;
	
	@Test
	public void testUploadPhotograph() {
		PhotographDto photograph = new PhotographDto();
		
		photograph.setArtworkTitle("Test Title");
		photograph.setArtistStatement("Test Statement");
		photograph.setArtworkURL("testPhotographURL");
		photograph.setIsPrint(false);
		photograph.setHeightInches(36.0);
		photograph.setWidthInches(24.0);
		photograph.setArtistID(1);
		
		Photograph savedPhotograph = photographController.uploadPhotograph(photograph);
		
		assertNotNull(savedPhotograph.getArtworkID());
		assertNotNull(savedPhotograph.getTitle());
		assertNotNull(savedPhotograph.getUrl());
		assertNotNull(savedPhotograph.getHeightInches());
		assertNotNull(savedPhotograph.getWidthInches());
		assertNotNull(savedPhotograph.getIsPrint());
		assertEquals(photograph.getArtworkTitle(), savedPhotograph.getTitle());
		assertEquals(photograph.getArtistStatement(), savedPhotograph.getArtistStatement());
		assertEquals(photograph.getArtworkURL(), savedPhotograph.getUrl());
		assertEquals(photograph.getIsPrint(), savedPhotograph.getIsPrint());
		assertEquals(photograph.getHeightInches(), savedPhotograph.getHeightInches());
		assertEquals(photograph.getWidthInches(), savedPhotograph.getWidthInches());
	}

}
