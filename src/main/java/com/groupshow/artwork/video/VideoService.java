package com.groupshow.artwork.video;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean uploadVideo(VideoDto videoDto) {
        User artist = userRepository.findById(videoDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found"));
        var video = new Video();

        video.setTitle(videoDto.getTitle());
        video.setArtworkType(videoDto.getArtworkType());
        video.setArtistStatement(videoDto.getArtistStatement());
        video.setArtist(artist);
        video.setBucketUrl(videoDto.getBucketUrl());
        video.setIsOpenForCritique(videoDto.getIsOpenForCritique());
        video.setVideoType(videoDto.getType());
        video.setDurationHours(videoDto.getDurationHours());
        video.setDurationMins(videoDto.getDurationMins());
        video.setDurationSecs(videoDto.getDurationSecs());

        videoRepository.save(video);
        return true;
    }

}
