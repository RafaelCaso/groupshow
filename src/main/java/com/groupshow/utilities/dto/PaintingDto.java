package com.groupshow.utilities.dto;

import com.groupshow.models.PaintingType;
import com.groupshow.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaintingDto {
    private PaintingType paintingType;
    private Double widthInches;
    private Double heightInches;
    private String title;
    private String statement;
    private Integer artistID;
    private String url;
    private Boolean isOpenForCritique;
}
