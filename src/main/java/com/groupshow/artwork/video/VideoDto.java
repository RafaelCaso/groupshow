package com.groupshow.artwork.video;

import com.groupshow.artwork.ArtworkType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoDto {
	private ArtworkType artworkType;
	private String title;
	private String artistStatement;
	private Integer artistID;
	private String bucketUrl;
	private Boolean isOpenForCritique;
	private VideoType videoType;
	private Integer durationHours;
	private Integer durationMins;
	private Integer durationSecs;

	
}
