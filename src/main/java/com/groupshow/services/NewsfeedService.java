package com.groupshow.services;

import com.groupshow.models.Artwork;
import com.groupshow.repositories.ArtworkRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NewsfeedService {

    @Autowired
    private ArtworkRepository artworkRepository;

    public List<Artwork> retrieveTwentyMostRecentArtworks() {
        return artworkRepository.findTop20ByOrderBySubmissionDateDesc();
    }

}
