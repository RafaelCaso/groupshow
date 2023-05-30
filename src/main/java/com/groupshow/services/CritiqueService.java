package com.groupshow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupshow.models.Critique;
import com.groupshow.repositories.CritiqueRepository;

@Service
@Transactional
public class CritiqueService {
	
	@Autowired
	private CritiqueRepository critiqueRepository;
	
	public Critique addCritique(Critique critique) {
		if (critique.getArtwork().getOpenForCritique()) {
			return critiqueRepository.save(critique);
		}

		throw new RuntimeException("Artwork is not open for critique.");
	}
}
