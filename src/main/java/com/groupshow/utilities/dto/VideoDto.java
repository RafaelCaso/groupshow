package com.groupshow.utilities.dto;

import com.groupshow.models.VideoType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

	private String artworkTitle;
	private String artistStatement;
	private Integer artistID;
	private String artworkURL;
	private Boolean openForCritique;
	private VideoType videoType;
	private Integer durationHour;
	private Integer durationMin;
	private Integer durationSec;

	
}
