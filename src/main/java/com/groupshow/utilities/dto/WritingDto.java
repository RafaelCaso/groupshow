package com.groupshow.utilities.dto;

import com.groupshow.models.WritingType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WritingDto {

	private String title;
	private String statement;
	private Integer artistID;
	private String url;
	private Boolean isOpenForCritique;
	private WritingType writingType;
	private Boolean isFiction;
	private Integer wordCount;

	
}
