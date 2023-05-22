package com.groupshow.utilities.dto;

import com.groupshow.models.WritingType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WritingDto {

	private String artworkTitle;
	
	private String artistStatement;
	
	private Integer artistID;
	
	private String artworkURL;
	
	private WritingType writingType;
	
	private Boolean fiction;
	
	private Integer wordCount;
	
	
}
