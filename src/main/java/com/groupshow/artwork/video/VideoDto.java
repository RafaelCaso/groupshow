package com.groupshow.artwork.video;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoDto {
	private String title;
	private String artistStatement;
	private Integer artistID;
	private String bucketUrl;
	private Boolean isOpenForCritique;
	private VideoType type;
	private Integer durationHours;
	private Integer durationMins;
	private Integer durationSecs;

	
}
