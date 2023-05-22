package com.groupshow.utilities.dto;

import com.groupshow.models.PerformanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDto {
    private PerformanceType performanceType;
    private Integer durationHour;
    private Integer durationMin;
    private Integer durationSec;
    private String artworkTitle;
    private String artistStatement;
    private Integer artistID;
    private String artworkURL;
}
