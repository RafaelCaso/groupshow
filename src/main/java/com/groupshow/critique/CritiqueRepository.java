package com.groupshow.critique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CritiqueRepository extends JpaRepository<Critique, Integer> {
    Optional<List<Critique>> findByArtwork(Integer artworkID);
}
