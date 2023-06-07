package com.groupshow.utilities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotographDto {
    private String title;
    private String statement;
    private Integer artistID;
    private String url;
    private Boolean isOpenForCritique;
    private Boolean isPrint;
    private Double widthInches;
    private Double heightInches;
}
