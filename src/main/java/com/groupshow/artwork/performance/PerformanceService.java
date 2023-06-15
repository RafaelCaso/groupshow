package com.groupshow.artwork.performance;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private UserRepository userRepository;

    public Boolean uploadPerformance(PerformanceDto performanceDto) {
        User artist = userRepository.findById(performanceDto.getArtistID()).get();
        var performance = new Performance();

        performance.setTitle(performanceDto.getTitle());
        performance.setArtistStatement(performanceDto.getArtistStatement());
        performance.setArtist(artist);
        performance.setBucketUrl(performanceDto.getBucketUrl());
        performance.setIsOpenForCritique(performanceDto.getIsOpenForCritique());
        performance.setType(performanceDto.getPerformanceType());
        performance.setDurationHours(performanceDto.getDurationHours());
        performance.setDurationMins(performanceDto.getDurationMins());
        performance.setDurationSecs(performanceDto.getDurationSecs());

        performanceRepository.save(performance);
        return true;
    }

}
