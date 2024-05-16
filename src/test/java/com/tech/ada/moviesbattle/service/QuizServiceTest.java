package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.dto.factorydto.FactoryQuizDto;
import com.tech.ada.moviesbattle.model.*;
import com.tech.ada.moviesbattle.repository.QuizRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private FactoryQuizDto<QuizDto, Quiz> factoryQuizDto;

    @InjectMocks
    private QuizService quizService;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetActiveQuiz() {

        Player player = new Player();
        Quiz quiz = new Quiz(LocalDateTime.now(), player, StatusQuiz.ACTIVE, 0);
        when(quizRepository.findActiveQuizByPlayer(player)).thenReturn(Optional.of(quiz));

        QuizDto quizDto = new QuizDto();
        quizDto.setId(1L);
        quizDto.setStatus("ACTIVE");
        quizDto.setStartDateTime("2024-05-15T19:44:30.867211041");
        when(factoryQuizDto.buildFromEntity(quiz)).thenReturn(quizDto);

        Optional<QuizDto> result = quizService.getActiveQuiz(player);

        assertEquals(Optional.of(quizDto), result);
    }

    @Test
    void testCheckIfNoActiveQuizCreateOne() {

        Player player = new Player();
        when(quizService.getActiveQuiz(player)).thenReturn(Optional.empty());

        QuizDto newQuizDto = new QuizDto();
        when(quizService.createNewQuiz(player)).thenReturn(newQuizDto);

        QuizDto result = quizService.checkIfNoActiveQuizCreateOne(player);
        assertEquals(newQuizDto, result);
    }

    @Test
    void testCreateNewQuiz() {

        Player player = new Player();
        Quiz savedQuiz = new Quiz();
        savedQuiz.setId(1L);
        savedQuiz.setMistakes(0);
        savedQuiz.setStartDateTime(LocalDateTime.now());
        savedQuiz.setStatus(StatusQuiz.ACTIVE);
        when(quizRepository.save(any(Quiz.class))).thenReturn(savedQuiz);

        QuizDto quizDto = new QuizDto();
        when(factoryQuizDto.buildFromEntity(savedQuiz)).thenReturn(quizDto);

        QuizDto result = quizService.createNewQuiz(player);
        assertEquals(quizDto, result);
    }

    @Test
    void testGetActiveQuizById() {

        Player player = new Player();
        Quiz quiz = new Quiz();
        quiz.setId(1L);
        quiz.setMistakes(0);
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setStartDateTime(LocalDateTime.now());

        when(quizRepository.findActiveQuiz(player, 1L)).thenReturn(Optional.of(quiz));
        Optional<Quiz> result = quizService.getActiveQuizById(player, 1L);
        assertTrue(result.isPresent());
        assertEquals(quiz, result.get());
    }

    @Test
    void testUpdateQuizCheckMistakes() {

        Quiz quiz = new Quiz();
        quiz.setMistakes(2);
        quiz.setId(1L);
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setStartDateTime(LocalDateTime.now());

        quizService.updateQuizCheckMistakes(quiz);
        assertEquals(StatusQuiz.ACTIVE, quiz.getStatus());
        quiz.setMistakes(3);
        quizService.updateQuizCheckMistakes(quiz);

        assertEquals(StatusQuiz.INACTIVE, quiz.getStatus());
    }

    @Test
    void testThereIsQuestionWithSameMovies() {

        Quiz quiz = new Quiz();
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Question question = new Question();
        question.setMovieList(List.of(movie1, movie2));
        quiz.setQuestionList(Collections.singletonList(question));

        boolean result = quizService.thereIsQuestionWithSameMovies(quiz, List.of(movie1, movie2));

        assertTrue(result);
        result = quizService.thereIsQuestionWithSameMovies(quiz, Collections.singletonList(movie1));
        assertFalse(result);
    }
}
