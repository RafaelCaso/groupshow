package com.groupshow.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@MappedSuperclass
@Data
public abstract class Artwork {
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

    // Add List<Critique>
}