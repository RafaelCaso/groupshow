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
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private WritingType type;
	
	@Column(nullable=false)
	private boolean isFiction;
	
	@Column(nullable = false)
	private Integer wordCount;
	
	
}
