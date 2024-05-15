package com.tech.ada.moviesbattle.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuizDtoTest {

    @Test
    void testToString() {
        QuizDto quizDto = new QuizDto();
        quizDto.setStartDateTime("2022-04-15T10:30:00");
        quizDto.setStatus("Active");
        quizDto.setId(1L);
        quizDto.setMistakes(2);

        String expected = "QuizDto{startDateTime='2022-04-15T10:30:00', status='Active', id=1, mistakes=2}";
        assertEquals(expected, quizDto.toString());
    }

    @Test
    void testEquals() {
        QuizDto quizDto1 = new QuizDto();
        quizDto1.setStartDateTime("2022-04-15T10:30:00");
        quizDto1.setStatus("Active");
        quizDto1.setId(1L);
        quizDto1.setMistakes(2);

        QuizDto quizDto2 = new QuizDto();
        quizDto2.setStartDateTime("2022-04-15T10:30:00");
        quizDto2.setStatus("Active");
        quizDto2.setId(1L);
        quizDto2.setMistakes(2);

        QuizDto quizDto3 = new QuizDto();
        quizDto3.setStartDateTime("2022-04-15T10:30:00");
        quizDto3.setStatus("Inactive");
        quizDto3.setId(2L);
        quizDto3.setMistakes(3);

        assertEquals(quizDto1, quizDto2);
        assertNotEquals(quizDto1, quizDto3);
    }
}
