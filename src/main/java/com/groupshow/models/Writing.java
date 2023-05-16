package com.groupshow.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int writingID;
	
	@Column(nullable = false)
	private WritingType writingType;
	
	@Column(nullable=false)
	private boolean fiction;
	
	@Column(nullable = false)
	private Integer wordCount;
	
	
}
