package com.groupshow.utilities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
		
	private String artworkTitle;
	
	private String artistStatement;
	
	private Integer artistID;
	
	private String artworkURL;

	private Integer durationMin;
	
	private Integer durationSec;
	
	private Boolean openForCritique;
}
