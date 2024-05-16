package com.tech.ada.moviesbattle.controller;

import com.tech.ada.moviesbattle.dto.ScoreDto;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    void getScore_ShouldReturnScoreDto_WhenPlayerIsAuthenticated() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        Player player = new Player();
        player.setUsername("username");
        when(playerService.getPlayerByUsername("username")).thenReturn(player);

        ScoreDto scoreDto = new ScoreDto();
        when(playerService.getScore()).thenReturn(scoreDto);

        ResponseEntity<?> responseEntity = playerController.getScore();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(scoreDto, responseEntity.getBody());
    }

    @Test
    void getScore_ShouldReturnUnauthorized() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();

        ResponseEntity<?> responseEntity = playerController.getScore();

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    void getScore_ShouldReturnUnauthorizedResponse() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();

        when(playerService.getPlayerByUsername("")).thenReturn(null);

        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(playerService.getPlayerByUsername("username")).thenReturn(null);

        ResponseEntity<?> responseEntity = playerController.getScore();

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}
