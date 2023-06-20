package com.groupshow.artwork.song;

import com.groupshow.artwork.Artwork;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="songs")
public class Song extends Artwork {

    @Column(name = "duration_mins", nullable = false)
    private Integer durationMins;

    @Column(name = "duration_secs", nullable = false)
    private Integer durationSecs;
}
