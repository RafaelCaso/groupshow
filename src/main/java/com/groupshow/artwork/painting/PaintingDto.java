package com.groupshow.artwork.painting;

import com.groupshow.artwork.ArtworkType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaintingDto {
    private ArtworkType artworkType;
    private String title;
    private String artistStatement;
    private Integer artistID;
    private String bucketUrl;
    private Boolean isOpenForCritique;
    private PaintingType paintingType;
    private PaintingUnits units;
    private Double width;
    private Double height;
}
