package com.groupshow.artwork.painting;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PaintingService {

    @Autowired
    private PaintingRepository paintingRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean uploadPainting(PaintingDto paintingDto) {
        User artist = userRepository.findById(paintingDto.getArtistID()).get();
        var painting = new Painting();

        painting.setTitle(paintingDto.getTitle());
        painting.setArtistStatement(paintingDto.getArtistStatement());
        painting.setArtist(artist);
        painting.setBucketUrl(paintingDto.getBucketUrl());
        painting.setIsOpenForCritique(paintingDto.getIsOpenForCritique());
        painting.setType(paintingDto.getPaintingType());
        painting.setUnits(paintingDto.getUnits());
        painting.setWidth(paintingDto.getWidth());
        painting.setHeight(paintingDto.getHeight());

        paintingRepository.save(painting);
        return true;
    }
}
