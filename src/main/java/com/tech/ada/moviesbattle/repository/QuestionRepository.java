package com.tech.ada.moviesbattle.repository;

import com.tech.ada.moviesbattle.model.Question;
import com.tech.ada.moviesbattle.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM com.tech.ada.moviesbattle.model.Question q WHERE q.quiz = :quiz AND q.status = 'UNANSWERED'")
    Optional<Question> findUnansweredByPlayer(@Param("quiz") Quiz quiz);
}
