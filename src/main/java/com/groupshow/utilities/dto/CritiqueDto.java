package com.groupshow.utilities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CritiqueDto {

	private String content;
	private Integer criticID;
	private Integer artworkID;
	
}
