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
    private Double paintingWidth;
    private Double paintingHeight;
    private String artworkTitle;
    private String artistStatement;
    private Integer artistID;
    private String artworkURL;
    private Boolean openForCritique;
}
