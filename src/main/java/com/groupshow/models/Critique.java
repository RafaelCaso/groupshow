package com.groupshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="critiques")
public class Critique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer critiqueID;
	
	private String content;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User critic;
	
	@ManyToOne
    @JoinColumn(name = "artwork_id")
	private Artwork artwork;
	

}
