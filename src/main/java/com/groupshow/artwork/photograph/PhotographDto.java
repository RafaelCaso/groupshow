package com.groupshow.artwork.photograph;

import com.groupshow.artwork.ArtworkType;

import com.groupshow.artwork.ArtworkUnits;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotographDto {
	private ArtworkType artworkType;
    private String title;
    private String artistStatement;
    private Integer artistID;
    private String bucketUrl;
    private Boolean isOpenForCritique;
    private Boolean isPrinted;
    private ArtworkUnits units;
    private Double width;
    private Double height;
}
