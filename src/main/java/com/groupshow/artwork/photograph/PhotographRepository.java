package com.groupshow.artwork.photograph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographRepository extends JpaRepository<Photograph, Integer> {

}
