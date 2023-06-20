package com.groupshow.artwork.writing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WritingDto {
	private String title;
	private String artistStatement;
	private Integer artistID;
	private String bucketUrl;
	private Boolean isOpenForCritique;
	private WritingType type;
	private Boolean isFiction;
	private Integer wordCount;

	
}
