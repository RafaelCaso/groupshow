package com.groupshow.services;

import com.groupshow.models.Performance;
import com.groupshow.repositories.PerformanceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Performance uploadPerformance(Performance performance) {
        return performanceRepository.save(performance);
    }

    public Performance retrievePerformance(Integer performanceID) {
        return performanceRepository.findById(performanceID).get();
    }
}
