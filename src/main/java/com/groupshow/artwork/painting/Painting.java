package com.groupshow.artwork.painting;

import com.groupshow.artwork.Artwork;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paintings")
public class Painting extends Artwork {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaintingType paintingType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaintingUnits units;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double height;
}
