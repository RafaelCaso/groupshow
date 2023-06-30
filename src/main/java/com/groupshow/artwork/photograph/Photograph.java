package com.groupshow.artwork.photograph;

import com.groupshow.artwork.Artwork;
import com.groupshow.artwork.ArtworkUnits;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "photographs")
public class Photograph extends Artwork {

    @Column(name = "is_printed", nullable = false)
    private Boolean isPrinted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArtworkUnits artworkUnits;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double height;
}
