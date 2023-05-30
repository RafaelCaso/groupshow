package com.groupshow.models;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Artwork {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="artwork_id")
    private Integer artworkID;
	
    @Column(nullable = false)
    private String artworkTitle;

    private String artistStatement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User artist;

    @Column(name = "submission_date", nullable = false)
    private Instant submissionDate;

    @PrePersist
    public void prePersistCreationDate() {
        submissionDate = Instant.now();
    }

    @Column(nullable = false)
    private String artworkURL;

    private Double averageScore;
    
    private Boolean openForCritique;

    
    //private List<Critique> critiques;
}