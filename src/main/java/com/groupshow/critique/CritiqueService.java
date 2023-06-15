package com.groupshow.critique;

import com.groupshow.artwork.Artwork;
import com.groupshow.artwork.ArtworkRepository;
import com.groupshow.artwork.ArtworkService;
import com.groupshow.artwork.painting.PaintingRepository;
import com.groupshow.artwork.performance.PerformanceRepository;
import com.groupshow.artwork.photograph.PhotographRepository;
import com.groupshow.artwork.song.SongRepository;
import com.groupshow.artwork.video.VideoRepository;
import com.groupshow.artwork.writing.WritingRepository;
import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CritiqueService {
	@Autowired
	private ArtworkService artworkService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CritiqueRepository critiqueRepository;
	
	public Boolean addCritique(CritiqueDto critiqueDto) {
		User critic = userRepository.findById(critiqueDto.getCriticID()).get();
		Artwork critiquedArtwork = artworkService.getArtworkByID(critiqueDto.getArtworkID());

		if (critiquedArtwork.getIsOpenForCritique()) {
			var critique = Critique.builder()
					.rating(critiqueDto.getRating())
					.content(critiqueDto.getContent())
					.critic(critic)
					.artwork(critiquedArtwork)
					.build();

			critiqueRepository.save(critique);
			return true;
		}
		return false;
	}
}
