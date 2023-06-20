package com.groupshow.critique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CritiqueRepository extends JpaRepository<Critique, Integer> {

}
