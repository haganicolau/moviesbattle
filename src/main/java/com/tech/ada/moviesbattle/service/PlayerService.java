package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.dto.ScoreDto;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The PlayerService class acts as a service layer component responsible for managing player-related operations and
 * interactions with the underlying data store.
 */
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Retrieves a player by their username from the database.
     * @param username
     * @return
     */
    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    /**
     * Updates an existing player in the database.
     * @return
     */
    public void updatePlayer(Player player) {
        this.playerRepository.save(player);
    }

    /**
     * Retrieves a list of all players from the database.
     * @return
     */
    public List<Player> getPlayers() {
        return this.playerRepository.findAll();
    }

    /**
     * Retrieves the scores of all players in the database and returns them as a ScoreDto object.
     * @return
     */
    public ScoreDto getScore() {
        ScoreDto dto = new ScoreDto();
        List<Player> playerList = getPlayers();
        Map<String, Integer> scores = new HashMap<String, Integer>();

        for (Player player : playerList) {
            scores.put(player.getUsername(), player.getScore());
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(scores.entrySet());
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        dto.setScores(scores);
        return dto;
    }

}
