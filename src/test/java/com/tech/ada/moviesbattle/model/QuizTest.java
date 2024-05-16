package com.tech.ada.moviesbattle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    @Test
    public void testToString() {
        Player player = new Player();
        player.setId(1L);
        player.setUsername("testuser");

        Quiz quiz = new Quiz();
        quiz.setId(1L);
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setPlayer(player);

        String expected = "Quiz{id=1, startDateTime=null, finishDateTime=null, player=Player{id=1, username='testuser', score=0, quizList=null}, questionList=null, status=ACTIVE, mistakes=0}";
        assertEquals(expected, quiz.toString());
    }

    @Test
    public void testEquals() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setUsername("testuser");

        Player player2 = new Player();
        player2.setId(1L);
        player2.setUsername("testuser");

        Player player3 = new Player();
        player3.setId(2L);
        player3.setUsername("testuser2");

        Quiz quiz1 = new Quiz();
        quiz1.setId(1L);
        quiz1.setStatus(StatusQuiz.ACTIVE);
        quiz1.setPlayer(player1);

        Quiz quiz2 = new Quiz();
        quiz2.setId(1L);
        quiz2.setStatus(StatusQuiz.ACTIVE);
        quiz2.setPlayer(player2);

        Quiz quiz3 = new Quiz();
        quiz3.setId(2L);
        quiz3.setStatus(StatusQuiz.ACTIVE);
        quiz3.setPlayer(player3);

        assertEquals(quiz1, quiz2);
        assertEquals(quiz1.hashCode(), quiz2.hashCode());
        assertNotEquals(quiz1, quiz3);
        assertNotEquals(quiz1.hashCode(), quiz3.hashCode());
    }

}
