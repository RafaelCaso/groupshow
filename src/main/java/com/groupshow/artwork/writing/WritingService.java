package com.groupshow.artwork.writing;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WritingService {
	
	@Autowired
	private WritingRepository writingRepository;

	@Autowired
	private UserRepository userRepository;
	
	public Boolean uploadWriting(WritingDto writingDto) {
		User artist = userRepository.findById(writingDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found"));
		var writing = new Writing();

		writing.setTitle(writingDto.getTitle());
		writing.setArtistStatement(writingDto.getArtistStatement());
		writing.setArtist(artist);
		writing.setType(writingDto.getType());
		writing.setBucketUrl(writingDto.getBucketUrl());
		writing.setIsOpenForCritique(writingDto.getIsOpenForCritique());
		writing.setIsFiction(writingDto.getIsFiction());
		writing.setWordCount(writingDto.getWordCount());

		writingRepository.save(writing);
		return true;
	}
}
