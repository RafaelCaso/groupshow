package com.groupshow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupshow.models.Writing;
import com.groupshow.repositories.WritingRepository;

@Service
@Transactional
public class WritingService {
	
	@Autowired
	private WritingRepository writingRepository;
	
	public Writing uploadWriting(Writing writing) {
		return writingRepository.save(writing);
	}
	
	public Writing retrieveWriting(Integer writingID) {
		return writingRepository.findById(writingID).get();
	}

}
