package com.tech.ada.moviesbattle.controller;

import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.service.PlayerService;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

    @Mock
    PlayerService playerService;

    @Mock
    QuizService quizService;

    @InjectMocks
    QuizController quizController;

    @BeforeEach
    void setUp() {
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    void getQuiz_PlayerAuthenticated_ReturnsQuizDto() {

        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Player player = new Player();
        player.setUsername("user1");
        player.setPassword("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
        player.setScore(0);
        when(playerService.getPlayerByUsername("username")).thenReturn(player);


        QuizDto quizDto = new QuizDto();
        quizDto.setStartDateTime("2022-04-15T10:30:00");
        quizDto.setStatus("Active");
        quizDto.setId(1L);
        quizDto.setMistakes(2);

        when(quizService.getActiveQuiz(any())).thenReturn(Optional.of(quizDto));
        ResponseEntity<?> response = quizController.getQuiz();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getQuiz_PlayerNotAuthenticated_ReturnsUnauthorized() {

        SecurityContextHolder.clearContext();
        ResponseEntity<?> response = quizController.getQuiz();
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getQuiz_PlayerNotFound_ReturnsUnauthorized() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(null);
        ResponseEntity<?> response = quizController.getQuiz();
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getQuiz_NoActiveQuiz_ReturnsNotFound() {

        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());
        when(quizService.getActiveQuiz(any())).thenReturn(Optional.empty());
        ResponseEntity<?> response = quizController.getQuiz();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createQuiz_PlayerAuthenticated_ReturnsQuizDto() {

        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(new Player());

        QuizDto quizDto = new QuizDto();
        quizDto.setStartDateTime("2022-04-15T10:30:00");
        quizDto.setStatus("Active");
        quizDto.setId(1L);
        quizDto.setMistakes(2);
        when(quizService.checkIfNoActiveQuizCreateOne(any())).thenReturn(quizDto);
        ResponseEntity<?> response = quizController.createQuiz();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createQuiz_PlayerNotAuthenticated_ReturnsUnauthorized() {

        SecurityContextHolder.clearContext();
        ResponseEntity<?> response = quizController.createQuiz();
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void createQuiz_PlayerAuthenticated_ReturnsUnauthorized() {

        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername(anyString())).thenReturn(null);
        ResponseEntity<?> response = quizController.createQuiz();
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

}
