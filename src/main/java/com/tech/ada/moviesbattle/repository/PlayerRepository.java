package com.tech.ada.moviesbattle.repository;

import com.tech.ada.moviesbattle.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByUsernameAndPassword(String username, String password);
    Optional<Player> findByUsername(String username);
}
