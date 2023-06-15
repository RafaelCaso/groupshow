package com.groupshow.repositories;

import com.groupshow.artwork.ArtworkRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ArtworkRepositoryTest {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Test
    public void testFindTop20ByOrderBySubmissionDateDesc() {

//        artworkRepository.save()
    }


}