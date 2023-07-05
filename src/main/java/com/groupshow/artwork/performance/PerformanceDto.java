package com.groupshow.artwork.performance;

import com.groupshow.artwork.ArtworkType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerformanceDto {
	private ArtworkType artworkType;
    private String title;
    private String artistStatement;
    private Integer artistID;
    private String bucketUrl;
    private Boolean isOpenForCritique;
    private PerformanceType performanceType;
    private Integer durationHours;
    private Integer durationMins;
    private Integer durationSecs;
}
