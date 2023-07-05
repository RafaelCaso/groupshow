package com.groupshow.artwork;

import com.groupshow.artwork.performance.Performance;
import com.groupshow.artwork.performance.PerformanceController;
import com.groupshow.artwork.performance.PerformanceDto;
import com.groupshow.artwork.performance.PerformanceType;
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
        performance.setDurationHours(2);
        performance.setDurationMins(40);
        performance.setDurationSecs(12);
        performance.setPerformanceType(PerformanceType.CLASSICAL);
        performance.setArtistID(1);

        Performance savedPerformance = performanceController.uploadPerformance(performance);


        assertNotNull(savedPerformance.getArtworkID());
        assertEquals(performance.getArtworkTitle(), savedPerformance.getTitle());
        assertNotNull(savedPerformance.getTitle());
        assertEquals(performance.getArtistStatement(), savedPerformance.getStatement());
        assertEquals(performance.getArtworkURL(), savedPerformance.getBucketUrl());
        assertNotNull(savedPerformance.getBucketUrl());
        assertEquals(performance.getDurationHours(), savedPerformance.getDurationHours());
        assertNotNull(savedPerformance.getDurationHours());
        assertEquals(performance.getDurationMins(), savedPerformance.getDurationMins());
        assertNotNull(savedPerformance.getDurationMins());
        assertEquals(performance.getDurationSecs(), savedPerformance.getDurationSecs());
        assertNotNull(savedPerformance.getDurationSecs());
        assertNotNull(performance.getPerformanceType());
        assertEquals(performance.getPerformanceType(), savedPerformance.getArtworkType());
    }
}
