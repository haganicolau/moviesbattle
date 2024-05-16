package com.tech.ada.moviesbattle.controller;

import com.tech.ada.moviesbattle.dto.AnswerDto;
import com.tech.ada.moviesbattle.dto.MovieDto;
import com.tech.ada.moviesbattle.dto.QuestionDto;
import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.service.PlayerService;
import com.tech.ada.moviesbattle.service.QuestionService;
import com.tech.ada.moviesbattle.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

    @Mock
    PlayerService playerService;

    @Mock
    QuizService quizService;

    @Mock
    QuestionService questionService;

    @InjectMocks
    QuestionController questionController;

    @BeforeEach
    void setUp() {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void createQuestion_PlayerAuthenticated_ReturnsQuestionDto() throws Exception {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());
        when(quizService.getActiveQuizById(any(), anyLong())).thenReturn(Optional.of(new Quiz()));

        QuizDto quizDto = new QuizDto();
        quizDto.setStartDateTime("2022-04-15T10:30:00");
        quizDto.setStatus("Active");
        quizDto.setId(1L);
        quizDto.setMistakes(0);

        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Title");
        movieDto.setYear("2022");
        movieDto.setImdbId("tt1234567");

        List<MovieDto> movies = new ArrayList<>();
        movies.add(movieDto);

        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuiz(quizDto);
        questionDto.setMovies(movies);
        questionDto.setStatus("Active");
        questionDto.setQuestionId(1L);

        when(questionService.createQuestionAndConvertDto(any())).thenReturn(new QuestionDto());

        ResponseEntity<?> response = questionController.createQuestion(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createQuestion_PlayerNotAuthenticated_ReturnsUnauthorized() {

        SecurityContextHolder.clearContext();
        ResponseEntity<?> response = questionController.createQuestion(1L);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void createQuestion_PlayerNotFound_ReturnsUnauthorized() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(null);
        ResponseEntity<?> response = questionController.createQuestion(1L);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void createQuestion_NoActiveQuiz_ReturnsNotFound() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());
        when(quizService.getActiveQuizById(any(), anyLong())).thenReturn(Optional.empty());
        ResponseEntity<?> response = questionController.createQuestion(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void answerQuestion_PlayerAuthenticated_ReturnsSuccessMessage() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());
        when(quizService.getActiveQuizById(any(), anyLong())).thenReturn(Optional.of(new Quiz()));
        when(questionService.validateAnswerQuestion(any(), any(), any())).thenReturn(true);
        ResponseEntity<?> response = questionController.answerQuestion(1L, new AnswerDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Right answer! You earned 1 point\"}", response.getBody());
    }

    @Test
    void answerQuestion_WrongAnswer_ReturnsSuccessMessage() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());
        when(quizService.getActiveQuizById(any(), anyLong())).thenReturn(Optional.of(new Quiz()));
        when(questionService.validateAnswerQuestion(any(), any(), any())).thenReturn(false);
        ResponseEntity<?> response = questionController.answerQuestion(1L, new AnswerDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"message\": \"Wrong answer! You noted 1 mistake.\"}", response.getBody());
    }

    @Test
    void answerQuestion_PlayerNotAuthenticated_ReturnsUnauthorized() {

        SecurityContextHolder.clearContext();
        ResponseEntity<?> response = questionController.answerQuestion(1L, new AnswerDto());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void answerQuestion_PlayerNotFound_ReturnsUnauthorized() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(null);
        ResponseEntity<?> response = questionController.answerQuestion(1L, new AnswerDto());
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void answerQuestion_NoActiveQuiz_ReturnsNotFound() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());
        when(quizService.getActiveQuizById(any(), anyLong())).thenReturn(Optional.empty());
        ResponseEntity<?> response = questionController.answerQuestion(1L, new AnswerDto());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void answerQuestion_CorrectAnswer_ReturnsSuccessMessage() {

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());
        when(quizService.getActiveQuizById(any(), anyLong())).thenReturn(Optional.of(new Quiz()));
        when(questionService.validateAnswerQuestion(any(), any(), any())).thenReturn(true);
        ResponseEntity<?> response = questionController.answerQuestion(1L, new AnswerDto());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
