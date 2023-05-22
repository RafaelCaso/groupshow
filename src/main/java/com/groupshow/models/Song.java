package com.groupshow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="songs")
public class Song extends Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songID;

    @Column(nullable = false)
    private Integer durationMin;

    @Column(nullable = false)
    private Integer durationSec;
}
