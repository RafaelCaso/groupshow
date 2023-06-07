package com.groupshow.utilities.dto;

import com.groupshow.models.VideoType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

	private String title;
	private String statement;
	private Integer artistID;
	private String url;
	private Boolean isOpenForCritique;
	private VideoType videoType;
	private Integer durationHour;
	private Integer durationMin;
	private Integer durationSec;

	
}
