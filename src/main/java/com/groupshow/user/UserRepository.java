package com.groupshow.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByRegistrationToken(String registrationToken);

    public Optional<User> findByEmail(String email);

}
