package com.groupshow.artwork.writing;

import com.groupshow.artwork.ArtworkType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WritingDto {
	private ArtworkType artworkType;
	private String title;
	private String artistStatement;
	private Integer artistID;
	private String bucketUrl;
	private Boolean isOpenForCritique;
	private WritingType writingType;
	private Boolean isFiction;
	private Integer wordCount;

	
}
