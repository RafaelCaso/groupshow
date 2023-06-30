package com.groupshow.artwork.writing;

import com.groupshow.artwork.Artwork;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "writings")
public class Writing extends Artwork {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WritingType writingType;

    @Column(name = "is_fiction", nullable = false)
    private Boolean isFiction;

    @Column(name = "word_count", nullable = false)
    private Integer wordCount;
}
