package com.groupshow.utilities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotographDto {
    private Boolean isPrint;
    private Double widthInches;
    private Double heightInches;
    private String artworkTitle;
    private String artistStatement;
    private Integer artistID;
    private String artworkURL;
}
