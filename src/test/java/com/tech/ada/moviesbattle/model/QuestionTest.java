package com.tech.ada.moviesbattle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @Test
    public void testToString() {
        Question question = new Question();
        question.setId(1L);
        question.setStatus(StatusQuestion.ANSWERED);

        String expected = "Question{id=1, quiz=null, movieList=null, status=ANSWERED}";
        assertEquals(expected, question.toString());
    }

    @Test
    public void testEquals() {
        Question question1 = new Question();
        question1.setId(1L);
        question1.setStatus(StatusQuestion.ANSWERED);

        Question question2 = new Question();
        question2.setId(1L);
        question2.setStatus(StatusQuestion.ANSWERED);

        Question question3 = new Question();
        question3.setId(2L);
        question3.setStatus(StatusQuestion.ANSWERED);

        assertEquals(question1, question2);
        assertEquals(question1.hashCode(), question2.hashCode());
        assertNotEquals(question1, question3);
        assertNotEquals(question1.hashCode(), question3.hashCode());
    }
}
