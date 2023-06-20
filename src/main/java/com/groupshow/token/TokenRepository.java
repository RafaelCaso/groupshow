package com.groupshow.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("SELECT t FROM Token t INNER JOIN t.user u " +
            "WHERE u.userID = :userID AND (t.isExpired = false OR t.isRevoked = false)")
    public List<Token> findAllValidTokensByUser(Integer userID);

    public Optional<Token> findByJwt(String jwt);
}
