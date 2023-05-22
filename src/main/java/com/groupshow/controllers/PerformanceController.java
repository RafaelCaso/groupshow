package com.groupshow.controllers;

import com.groupshow.models.Performance;
import com.groupshow.services.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance")
@CrossOrigin(origins = "http://localhost:3000")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/upload")
    public Performance uploadPerformance(@RequestBody Performance performance) {
        return performanceService.uploadPerformance(performance);
    }
}
