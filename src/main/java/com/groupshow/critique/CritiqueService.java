package com.groupshow.critique;

import com.groupshow.artwork.Artwork;
import com.groupshow.artwork.ArtworkRepository;
import com.groupshow.artwork.ArtworkService;
import com.groupshow.exceptions.UserNotFoundException;
import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CritiqueService {
	private final ArtworkService artworkService;
	private final UserRepository userRepository;
	private final CritiqueRepository critiqueRepository;
	private final ArtworkRepository artworkRepository;

	public Boolean addCritique(CritiqueDto critiqueDto) throws UserNotFoundException {
		User critic = userRepository.findById(critiqueDto.getCriticID()).orElseThrow(() -> new UserNotFoundException("ID"));
		Artwork critiquedArtwork = artworkService.getSingleArtworkByID(critiqueDto.getArtworkID());

		if (critiquedArtwork.getIsOpenForCritique()) {
			var critique = Critique.builder()
					.rating(critiqueDto.getRating())
					.content(critiqueDto.getContent())
					.critic(critic)
					.artwork(critiquedArtwork)
					.build();

			critiqueRepository.save(critique);

			List<Critique> critiques = critiquedArtwork.getCritiques();
			critiques.add(critique);
			critiquedArtwork.setCritiques(critiques);

			artworkRepository.save(critiquedArtwork);

			return true;
		}
		return false;
	}

	public List<Critique> getCritiquesByArtworkID(Integer artworkID) {
		List<Critique> critiques = critiqueRepository.findAll()
				.stream()
				.filter(critique -> critique.getArtwork().getArtworkID() == artworkID)
				.collect(Collectors.toList());
		return critiques;
	}
}
