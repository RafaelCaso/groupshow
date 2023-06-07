package com.groupshow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="critiques")
public class Critique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer critiqueID;

	@Column(nullable = false)
	private Integer rating;
	
	private String content;

	@Column(name = "submission_date", nullable = false)
	private Instant submissionDate;

	@PrePersist
	public void prePersistSubmissionDate() {
		submissionDate = Instant.now();
	}
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User critic;
	
	@ManyToOne
    @JoinColumn(name = "artwork_id")
	private Artwork artwork;
	

}
