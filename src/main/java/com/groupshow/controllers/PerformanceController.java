package com.groupshow.controllers;

import com.groupshow.models.Performance;
import com.groupshow.models.User;
import com.groupshow.repositories.UserRepository;
import com.groupshow.services.PerformanceService;
import com.groupshow.utilities.dto.PerformanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance")
@CrossOrigin(origins = "http://localhost:3000")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/upload")
    public Performance uploadPerformance(@RequestBody PerformanceDto performanceDto) {
        User artist = userRepository.findById(performanceDto.getArtistID()).orElseThrow(() -> new RuntimeException("User not found."));

        Performance performance = new Performance();

        performance.setPerformanceType(performanceDto.getPerformanceType());
        performance.setDurationHour(performanceDto.getDurationHour());
        performance.setDurationMin(performanceDto.getDurationMin());
        performance.setDurationSec(performanceDto.getDurationSec());
        performance.setArtworkTitle(performanceDto.getArtworkTitle());
        performance.setArtistStatement(performanceDto.getArtistStatement());
        performance.setArtist(artist);
        performance.setArtworkURL(performanceDto.getArtworkURL());

        return performanceService.uploadPerformance(performance);
    }
}
