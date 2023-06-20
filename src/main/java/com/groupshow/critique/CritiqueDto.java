package com.groupshow.critique;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CritiqueDto {
	private Integer rating;
	private String content;
	private Integer criticID;
	private Integer artworkID;
}
