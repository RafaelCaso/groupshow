package com.groupshow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="paintings")
public class Painting extends Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paintingID;

    @Column(nullable = false)
    private PaintingType paintingType;

    @Column(nullable = false)
    private Double paintingWidth;

    @Column(nullable = false)
    private Double paintingHeight;
}
