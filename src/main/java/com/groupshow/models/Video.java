package com.groupshow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="videos")
public class Video extends Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videoID;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private VideoType videoType;

    @Column(nullable = false)
    private Integer durationHour;

    @Column(nullable = false)
    private Integer durationMin;

    @Column(nullable = false)
    private Integer durationSec;
}
