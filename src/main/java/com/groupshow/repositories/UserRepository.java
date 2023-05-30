package com.groupshow.repositories;

import com.groupshow.models.Artwork;
import com.groupshow.models.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.groupshow.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByRegTokenID(String regTokenID);

    public User findByEmail(String email);

}
