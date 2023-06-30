package com.groupshow.artwork.painting;

import com.groupshow.artwork.Artwork;
import com.groupshow.artwork.ArtworkUnits;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "paintings")
public class Painting extends Artwork {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaintingType paintingType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArtworkUnits artworkUnits;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double height;
}
