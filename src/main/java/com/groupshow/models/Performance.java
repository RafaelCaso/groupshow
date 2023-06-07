package com.groupshow.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="performances")
public class Performance extends Artwork {
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerformanceType type;

    @Column(nullable = false)
    private Integer durationHour;

    @Column(nullable = false)
    private Integer durationMin;

    @Column(nullable = false)
    private Integer durationSec;
}
