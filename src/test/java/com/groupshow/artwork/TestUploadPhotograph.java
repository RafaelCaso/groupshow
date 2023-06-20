package com.groupshow.artwork;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.groupshow.artwork.photograph.Photograph;
import com.groupshow.artwork.photograph.PhotographController;
import com.groupshow.artwork.photograph.PhotographDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		photograph.setIsPrinted(false);
		photograph.setHeight(36.0);
		photograph.setWidth(24.0);
		photograph.setArtistID(1);
		
		Photograph savedPhotograph = photographController.uploadPhotograph(photograph);
		
		assertNotNull(savedPhotograph.getArtworkID());
		assertNotNull(savedPhotograph.getTitle());
		assertNotNull(savedPhotograph.getBucketUrl());
		assertNotNull(savedPhotograph.getHeightInches());
		assertNotNull(savedPhotograph.getWidthInches());
		assertNotNull(savedPhotograph.getIsPrinted());
		assertEquals(photograph.getArtworkTitle(), savedPhotograph.getTitle());
		assertEquals(photograph.getArtistStatement(), savedPhotograph.getStatement());
		assertEquals(photograph.getArtworkURL(), savedPhotograph.getBucketUrl());
		assertEquals(photograph.getIsPrinted(), savedPhotograph.getIsPrinted());
		assertEquals(photograph.getHeight(), savedPhotograph.getHeightInches());
		assertEquals(photograph.getWidth(), savedPhotograph.getWidthInches());
	}

}
