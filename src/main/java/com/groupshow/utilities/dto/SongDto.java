package com.groupshow.utilities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
		
	private String title;
	
	private String statement;
	
	private Integer artistID;
	
	private String url;

	private Integer durationMin;
	
	private Integer durationSec;
	
	private Boolean isOpenForCritique;
}
