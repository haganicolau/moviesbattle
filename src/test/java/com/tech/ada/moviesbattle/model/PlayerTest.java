package com.tech.ada.moviesbattle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testToString() {
        Player player = new Player();
        player.setUsername("john_doe");
        player.setPassword("password123");
        player.setScore(1000);

        String expected = "Player{id=null, username='john_doe', score=1000, quizList=null}";
        assertEquals(expected, player.toString());
    }

    @Test
    public void testEquals() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setUsername("alice");

        Player player2 = new Player();
        player2.setId(1L);
        player2.setUsername("alice");

        Player player3 = new Player();
        player3.setId(2L);
        player3.setUsername("bob");

        assertEquals(player1, player2);
        assertNotEquals(player1, player3);
    }

}
