package com.groupshow.artwork.song;

import com.groupshow.artwork.ArtworkType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongDto {
	private String title;
	private String artistStatement;
	private Integer artistID;
	private String bucketUrl;
	private Boolean isOpenForCritique;
	private Integer durationMins;
	private Integer durationSecs;
}
