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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="photographs")
public class Photograph extends Artwork {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int photgraphID;
	
	@Column(nullable=false)
	private boolean isPrint;
	
	@Column(nullable=false)
	private Double widthInches;
	
	@Column(nullable=false)
	private Double lengthInches;

}
