package com.groupshow.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Artwork {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artwork_seq")
    @SequenceGenerator(name = "artwork_seq", sequenceName = "artwork_seq", allocationSize = 1)
	@Column(name="artwork_id")
    private Long artworkID;
	
    @Column(nullable = false)
    private String artworkTitle;

    private String artistStatement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User artist;

    // @Column(nullable = false)
    private Date submissionDate;

    @Column(nullable = false)
    private String artworkURL;

    private Double averageScore;
    
    private Boolean openForCritique;

    
    //private List<Critique> critiques;
}