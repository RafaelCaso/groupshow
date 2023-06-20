package com.groupshow.artwork.performance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerformanceDto {
    private String title;
    private String artistStatement;
    private Integer artistID;
    private String bucketUrl;
    private Boolean isOpenForCritique;
    private PerformanceType type;
    private Integer durationHours;
    private Integer durationMins;
    private Integer durationSecs;
}
