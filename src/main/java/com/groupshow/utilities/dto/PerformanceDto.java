package com.groupshow.utilities.dto;

import com.groupshow.models.PerformanceGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerformanceDto {
    private String title;
    private String statement;
    private Integer artistID;
    private String url;
    private Boolean isOpenForCritique;
    private PerformanceGenre genre;
    private Integer durationHour;
    private Integer durationMin;
    private Integer durationSec;
}
