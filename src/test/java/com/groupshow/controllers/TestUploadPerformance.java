package com.groupshow.controllers;

import com.groupshow.models.Performance;
import com.groupshow.models.PerformanceType;

import com.groupshow.utilities.dto.PerformanceDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestUploadPerformance {

    @Autowired
    private PerformanceController performanceController;

    @Test
    public void testUploadPerformance() {
        PerformanceDto performance = new PerformanceDto();

        performance.setArtworkTitle("TestTitle");
        performance.setArtistStatement("TestStatement");
        performance.setArtworkURL("TestURL");
        performance.setDurationHour(2);
        performance.setDurationMin(40);
        performance.setDurationSec(12);
        performance.setPerformanceType(PerformanceType.CLASSICAL);
        performance.setArtistID(1);

        Performance savedPerformance = performanceController.uploadPerformance(performance);


        assertNotNull(savedPerformance.getArtworkID());
        assertEquals(performance.getArtworkTitle(), savedPerformance.getArtworkTitle());
        assertNotNull(savedPerformance.getArtworkTitle());
        assertEquals(performance.getArtistStatement(), savedPerformance.getArtistStatement());
        assertEquals(performance.getArtworkURL(), savedPerformance.getArtworkURL());
        assertNotNull(savedPerformance.getArtworkURL());
        assertEquals(performance.getDurationHour(), savedPerformance.getDurationHour());
        assertNotNull(savedPerformance.getDurationHour());
        assertEquals(performance.getDurationMin(), savedPerformance.getDurationMin());
        assertNotNull(savedPerformance.getDurationMin());
        assertEquals(performance.getDurationSec(), savedPerformance.getDurationSec());
        assertNotNull(savedPerformance.getDurationSec());
        assertNotNull(performance.getPerformanceType());
        assertEquals(performance.getPerformanceType(), savedPerformance.getPerformanceType());
    }
}
