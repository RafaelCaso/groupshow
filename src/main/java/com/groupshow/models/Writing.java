package com.groupshow.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="writings")
public class Writing extends Artwork {

	@Column(nullable = false)
	private final String artworkType = "Writing";

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WritingGenre genre;
	
	@Column(nullable=false)
	private boolean isFiction;
	
	@Column(nullable = false)
	private Integer wordCount;
	
	
}
