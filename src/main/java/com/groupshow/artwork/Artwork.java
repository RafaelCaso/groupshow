package com.groupshow.artwork;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groupshow.critique.Critique;
import com.groupshow.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_id")
    private Integer artworkID;

    @Column(nullable = false)
    private ArtworkType artworkType;

    @Column(nullable = false)
    private String title;

    @Column(name = "artist_statement")
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

    @Column(name = "bucket_url", nullable = false)
    private String bucketUrl;

    @Column(name = "average_score")
    private Double averageScore;

    @Column(name = "is_open_for_critique", nullable = false)
    private Boolean isOpenForCritique = false;

    @OneToMany(mappedBy = "artwork", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Critique> critiques;
}