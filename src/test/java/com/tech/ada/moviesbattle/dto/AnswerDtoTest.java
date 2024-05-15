package com.tech.ada.moviesbattle.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AnswerDtoTest {

    @Test
    void testToString() {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setTitleMovie("Title");
        answerDto.setQuestionId(1L);
        answerDto.setImdbId("tt1234567");

        String expected = "AnswerDto{titleMovie='Title', questionId=1, imdbId='tt1234567'}";
        assertEquals(expected, answerDto.toString());
    }

    @Test
    void testEquals() {
        AnswerDto answerDto1 = new AnswerDto();
        answerDto1.setTitleMovie("Title");
        answerDto1.setQuestionId(1L);
        answerDto1.setImdbId("tt1234567");

        AnswerDto answerDto2 = new AnswerDto();
        answerDto2.setTitleMovie("Title");
        answerDto2.setQuestionId(1L);
        answerDto2.setImdbId("tt1234567");

        AnswerDto answerDto3 = new AnswerDto();
        answerDto3.setTitleMovie("Title");
        answerDto3.setQuestionId(2L);
        answerDto3.setImdbId("tt1234567");

        assertEquals(answerDto1, answerDto2);
        assertNotEquals(answerDto1, answerDto3);
    }

}
