package com.groupshow.artwork.photograph;

import com.groupshow.artwork.ArtworkUnits;
import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhotographService {

	@Autowired
	private PhotographRepository photographRepository;

	@Autowired
	private UserRepository userRepository;

	public Boolean uploadPhotograph(PhotographDto photographDto) {
		User artist = userRepository.findById(photographDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found."));
		var photograph = new Photograph();

		photograph.setTitle(photographDto.getTitle());
		photograph.setArtworkType(photographDto.getArtworkType());
		photograph.setArtistStatement(photographDto.getArtistStatement());
		photograph.setArtist(artist);
		photograph.setBucketUrl(photographDto.getBucketUrl());
		photograph.setIsOpenForCritique(photographDto.getIsOpenForCritique());
		photograph.setIsPrinted(true);
		photograph.setArtworkUnits(ArtworkUnits.IN);
		photograph.setWidth(photographDto.getWidth());
		photograph.setHeight(photographDto.getHeight());

		photographRepository.save(photograph);
		return true;
	}
	
}
