package com.groupshow.utilities.dto;

import com.groupshow.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserArtworkDto {
    private List<Painting> paintings;
    private List<Performance> performances;
    private List<Photograph> photographs;
    private List<Song> songs;
    private List<Video> videos;
    private List<Writing> writings;
}
