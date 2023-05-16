package com.groupshow.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;


@MappedSuperclass
@Data
public abstract class Artwork {
    @Column(nullable = false)
    private String artworkTitle;

    private String artistStatement;

    @Column(nullable = false)
    private int artistID;

    // @Column(nullable = false)
    private Date submissionDate;

    @Column(nullable = false)
    private String artworkURL;

    private Double averageScore;

    // Add List<Critique>
}