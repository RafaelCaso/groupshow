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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int performanceID;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerformanceType performanceType;

    @Column(nullable = false)
    private Integer durationHour;

    @Column(nullable = false)
    private Integer durationMin;

    @Column(nullable = false)
    private Integer durationSec;
}
