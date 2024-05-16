package com.tech.ada.moviesbattle.repository;

import com.tech.ada.moviesbattle.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    QuizRepository quizRepository;

    @Test
    public void findUnansweredByPlayer() {

        Quiz quiz = new Quiz();
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setStartDateTime(LocalDateTime.now());
        quizRepository.save(quiz);

        Question question = new Question();
        question.setQuiz(quiz);
        List<Movie> lista = movieRepository.findRandomMovies(2);
        question.setMovieList(lista);
        question.setStatus(StatusQuestion.UNANSWERED);

        questionRepository.save(question);

        Optional<Question> optionalQuestion = questionRepository.findUnansweredByPlayer(quiz);
        assertTrue(optionalQuestion.isPresent());
    }

    @Test
    public void notFindUnansweredByPlayer() {

        Quiz quiz = new Quiz();
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setStartDateTime(LocalDateTime.now());
        quizRepository.save(quiz);

        Question question = new Question();
        question.setQuiz(quiz);
        List<Movie> lista = movieRepository.findRandomMovies(2);
        question.setMovieList(lista);
        question.setStatus(StatusQuestion.ANSWERED);

        questionRepository.save(question);

        Optional<Question> optionalQuestion = questionRepository.findUnansweredByPlayer(quiz);
        assertTrue(optionalQuestion.isEmpty());
    }

}
