package com.groupshow.artwork.video;

import com.groupshow.artwork.Artwork;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="videos")
public class Video extends Artwork {

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private VideoType videoType;

    @Column(name = "duration_hours", nullable = false)
    private Integer durationHours;

    @Column(name = "duration_mins", nullable = false)
    private Integer durationMins;

    @Column(name = "duration_secs", nullable = false)
    private Integer durationSecs;
}
