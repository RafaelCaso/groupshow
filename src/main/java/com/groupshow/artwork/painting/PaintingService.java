package com.groupshow.artwork.painting;

import com.groupshow.artwork.ArtworkUnits;
import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PaintingService {

    private final PaintingRepository paintingRepository;
    private final UserRepository userRepository;

    public Boolean uploadPainting(PaintingDto paintingDto) {
        User artist = userRepository.findById(paintingDto.getArtistID()).get();
        var painting = new Painting();

        painting.setTitle(paintingDto.getTitle());
        painting.setArtworkType(paintingDto.getArtworkType());
        painting.setArtistStatement(paintingDto.getArtistStatement());
        painting.setArtist(artist);
        painting.setBucketUrl(paintingDto.getBucketUrl());
        painting.setIsOpenForCritique(true);
        painting.setPaintingType(paintingDto.getPaintingType());
        painting.setArtworkUnits(ArtworkUnits.IN);
        painting.setWidth(paintingDto.getWidth());
        painting.setHeight(paintingDto.getHeight());

        paintingRepository.save(painting);
        return true;
    }
}
