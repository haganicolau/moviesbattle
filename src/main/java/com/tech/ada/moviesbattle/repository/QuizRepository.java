package com.tech.ada.moviesbattle.repository;


import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("SELECT q FROM com.tech.ada.moviesbattle.model.Quiz q WHERE q.player = :player AND q.status = 'ACTIVE'")
    Optional<Quiz> findActiveQuizByPlayer(@Param("player") Player player);

    @Query("SELECT q FROM com.tech.ada.moviesbattle.model.Quiz q WHERE q.player = :player AND q.status = 'ACTIVE' " +
            "AND q.id = :id")
    Optional<Quiz> findActiveQuiz(@Param("player") Player player, @Param("id") Long id);
}
