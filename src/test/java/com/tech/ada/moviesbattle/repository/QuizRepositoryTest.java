package com.tech.ada.moviesbattle.repository;

import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.model.StatusQuiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class QuizRepositoryTest {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void findActiveQuizByPlayerTest() {
        Optional<Player> optionalPlayer = playerRepository.findByUsername("user1");

        Quiz quiz = new Quiz();
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setStartDateTime(LocalDateTime.now());
        quiz.setPlayer(optionalPlayer.get());
        quizRepository.save(quiz);

        Optional<Quiz> optionalQuiz = quizRepository.findActiveQuizByPlayer(optionalPlayer.get());
        assertTrue(optionalQuiz.isPresent());
    }

    @Test
    public void notFindActiveQuizByPlayerTest() {
        Optional<Player> optionalPlayer = playerRepository.findByUsername("user1");

        Optional<Quiz> optionalQuiz = quizRepository.findActiveQuizByPlayer(optionalPlayer.get());
        assertTrue(optionalQuiz.isEmpty());
    }

}
