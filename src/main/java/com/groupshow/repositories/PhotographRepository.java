package com.groupshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupshow.models.Photograph;

@Repository
public interface PhotographRepository extends JpaRepository<Photograph, Integer> {

}
