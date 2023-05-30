package com.groupshow.repositories;

import com.groupshow.models.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<Artwork, Integer> {

    public List<Artwork> findTop20ByOrderBySubmissionDateDesc();
}
