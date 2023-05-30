package com.groupshow.utilities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotographDto {
    private String artworkTitle;
    private String artistStatement;
    private Integer artistID;
    private String artworkURL;
    private Boolean openForCritique;
    private Boolean isPrint;
    private Double widthInches;
    private Double heightInches;
}
