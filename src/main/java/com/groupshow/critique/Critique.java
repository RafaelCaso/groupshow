package com.groupshow.critique;

import com.groupshow.artwork.Artwork;
import com.groupshow.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
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
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User critic;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artwork_id")
	private Artwork artwork;
}
