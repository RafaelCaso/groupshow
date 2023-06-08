package com.groupshow.utilities.dto;

import com.groupshow.models.PaintingGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaintingDto {
    private PaintingGenre genre;
    private Double widthInches;
    private Double heightInches;
    private String title;
    private String statement;
    private Integer artistID;
    private String url;
    private Boolean isOpenForCritique;
}
