package com.groupshow.artwork.performance;

import com.groupshow.artwork.Artwork;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="performances")
public class Performance extends Artwork {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerformanceType type;

    @Column(name = "duration_hours", nullable = false)
    private Integer durationHours;

    @Column(name = "duration_mins", nullable = false)
    private Integer durationMins;

    @Column(name = "duration_secs", nullable = false)
    private Integer durationSecs;
}
