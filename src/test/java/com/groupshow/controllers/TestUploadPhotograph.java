package com.groupshow.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
		Photograph photograph = new Photograph();
		
		photograph.setArtworkTitle("Test Title");
		photograph.setArtistStatement("Test Statement");
		photograph.setArtworkURL("testphotographURL");
		photograph.setPrint(true);
		photograph.setLengthInches(8.5);
		photograph.setWidthInches(11.0);
		
		Photograph savedPhotograph = photographController.uploadPhotograph(photograph);
		
		assertNotNull(savedPhotograph.getPhotgraphID());
		assertNotNull(savedPhotograph.getArtworkTitle());
		assertNotNull(savedPhotograph.getArtworkURL());
		assertNotNull(savedPhotograph.getLengthInches());
		assertNotNull(savedPhotograph.getWidthInches());
		assertNotNull(savedPhotograph.isPrint());
		assertEquals(photograph.getArtworkTitle(), savedPhotograph.getArtworkTitle());
		assertEquals(photograph.getArtistStatement(), savedPhotograph.getArtistStatement());
		assertEquals(photograph.getArtworkURL(), savedPhotograph.getArtworkURL());
		assertEquals(photograph.isPrint(), savedPhotograph.isPrint());
		assertEquals(photograph.getLengthInches(), savedPhotograph.getLengthInches());
		assertEquals(photograph.getWidthInches(), savedPhotograph.getWidthInches());
	}
	
}
