package com.tech.ada.moviesbattle.repository;

import com.tech.ada.moviesbattle.model.Player;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void findByUsernameAndPasswordTest() {
        Optional<Player> player = playerRepository.findByUsernameAndPassword("user1",
                "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
        assertTrue(player.isPresent());
        assertEquals("user1", player.get().getUsername());
    }

    @Test
    public void notFindByUsernameAndPasswordTest() {
        Optional<Player> player = playerRepository.findByUsernameAndPassword("user1",
                "teste");
        assertTrue(player.isEmpty());
    }

    @Test
    public void findByUsernameTest() {
        Optional<Player> player = playerRepository.findByUsername("user1");
        assertTrue(player.isPresent());
        assertEquals("user1", player.get().getUsername());
    }

}
