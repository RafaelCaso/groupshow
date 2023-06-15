package com.groupshow.artwork;

import com.groupshow.artwork.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<Artwork, Integer> {

    public Artwork findByArtworkID(Integer artworkID);
    public List<Artwork> findTop20ByOrderBySubmissionDateDesc();
}
