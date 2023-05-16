package com.groupshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupshow.models.Writing;

@Repository
public interface WritingRepository extends JpaRepository<Writing, Integer> {

}
