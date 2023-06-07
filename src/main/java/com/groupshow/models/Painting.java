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
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaintingType type;

    @Column(nullable = false)
    private Double widthInches;

    @Column(nullable = false)
    private Double heightInches;
}
