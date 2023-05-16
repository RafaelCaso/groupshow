package com.groupshow.services;

import com.groupshow.models.Painting;
import com.groupshow.repositories.PaintingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaintingService {

    @Autowired
    private PaintingRepository paintingRepository;

    public Painting uploadPainting(Painting painting) {
        return paintingRepository.save(painting);
    }
}
