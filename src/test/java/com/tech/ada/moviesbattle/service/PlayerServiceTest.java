package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.dto.ScoreDto;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPlayerByUsername() {
        String username = "testUser";
        Player player = new Player();
        player.setUsername(username);
        when(playerRepository.findByUsername(username)).thenReturn(Optional.of(player));

        Player result = playerService.getPlayerByUsername(username);
        assertEquals(username, result.getUsername());
    }

    @Test
    void testUpdatePlayer() {
        Player player = new Player();
        playerService.updatePlayer(player);
        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void testGetPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        when(playerRepository.findAll()).thenReturn(players);

        List<Player> result = playerService.getPlayers();
        assertEquals(players, result);
    }

    @Test
    void testGetScore() {

        List<Player> players = new ArrayList<>();
        Player player1 = new Player();
        player1.setId(1L);
        player1.setUsername("user1");
        player1.setScore(100);
        players.add(player1);

        Player player2 = new Player();
        player2.setId(1L);
        player2.setUsername("user2");
        player2.setScore(200);
        players.add(player2);
        players.add(player2);

        when(playerRepository.findAll()).thenReturn(players);

        ScoreDto scoreDto = playerService.getScore();

        Map<String, Integer> scores = scoreDto.getScores();
        assertEquals(100, scores.get("user1"));
        assertEquals(200, scores.get("user2"));
    }

}
