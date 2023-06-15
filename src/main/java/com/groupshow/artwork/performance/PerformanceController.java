package com.groupshow.artwork.performance;

import com.groupshow.user.User;
import com.groupshow.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance")
@CrossOrigin(origins = "*")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @PostMapping("/upload")
    public ResponseEntity<Boolean> uploadPerformance(@RequestBody PerformanceDto performanceDto) {
        return ResponseEntity.ok(performanceService.uploadPerformance(performanceDto));
    }
}
