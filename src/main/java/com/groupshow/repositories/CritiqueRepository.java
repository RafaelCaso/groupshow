package com.groupshow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupshow.models.Critique;

@Repository
public interface CritiqueRepository extends JpaRepository<Critique, Integer> {

}
