package com.insearchofknowledge.bugTracker.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

//    @Query("SELECT t FROM Token t INNER JOIN Developer d ON t.developerId = d.id WHERE d.id = :id AND (t. isExpired = false or t.isRevoked = false)")
    @Query("SELECT t FROM Token t WHERE t.developer.id = :id AND (t.isExpired = false OR t.isRevoked = false)")
    List<Token> findAllValidTokensByUser(@Param("id") String id);

    Optional<Token> findByToken(String token);
}
