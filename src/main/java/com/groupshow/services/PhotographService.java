package com.groupshow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupshow.models.Photograph;
import com.groupshow.repositories.PhotographRepository;

@Service
@Transactional
public class PhotographService {

	@Autowired
	private PhotographRepository photographRepository;
	
	public Photograph uploadPhotograph(Photograph photograph) {
		return photographRepository.save(photograph);
	}
	
}
