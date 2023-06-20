package com.groupshow.artwork.photograph;

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
		photograph.setArtistStatement(photographDto.getArtistStatement());
		photograph.setArtist(artist);
		photograph.setBucketUrl(photographDto.getBucketUrl());
		photograph.setIsOpenForCritique(photographDto.getIsOpenForCritique());
		photograph.setIsPrinted(photographDto.getIsPrinted());
		photograph.setUnits(photographDto.getUnits());
		photograph.setWidth(photographDto.getWidth());
		photograph.setHeight(photographDto.getHeight());

		photographRepository.save(photograph);
		return true;
	}
	
}
