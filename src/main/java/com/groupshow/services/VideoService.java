package com.groupshow.services;

import com.groupshow.models.Video;
import com.groupshow.repositories.VideoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video uploadVideo(Video video) {
        return videoRepository.save(video);
    }

    public Video retrieveVideo(Integer videoID) {
        return videoRepository.findById(videoID).get();
    }
}
