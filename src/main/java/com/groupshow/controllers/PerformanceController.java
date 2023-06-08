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

        performance.setGenre(performanceDto.getGenre());
        performance.setDurationHour(performanceDto.getDurationHour());
        performance.setDurationMin(performanceDto.getDurationMin());
        performance.setDurationSec(performanceDto.getDurationSec());
        performance.setTitle(performanceDto.getTitle());
        performance.setStatement(performanceDto.getStatement());
        performance.setArtist(artist);
        performance.setUrl(performanceDto.getUrl());
        performance.setIsOpenForCritique(performanceDto.getIsOpenForCritique());

        return performanceService.uploadPerformance(performance);
    }
}
