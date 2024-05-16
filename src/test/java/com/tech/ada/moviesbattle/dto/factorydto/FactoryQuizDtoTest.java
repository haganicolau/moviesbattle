package com.tech.ada.moviesbattle.dto.factorydto;

import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.model.StatusQuiz;
import com.tech.ada.moviesbattle.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FactoryQuizDtoTest {

    @Mock
    private DateUtil dateUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuildFromEntity() {
        Quiz quiz = new Quiz();
        quiz.setId(1L);
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setMistakes(0);
        LocalDateTime startDateTime = LocalDateTime.now();
        quiz.setStartDateTime(startDateTime);

        FactoryQuizDto<QuizDto, Quiz> factoryQuizDto = new FactoryQuizDto<>(dateUtil);
        when(dateUtil.localDateTimeToString(startDateTime)).thenReturn("2022-04-12T12:00:00");
        QuizDto quizDto = factoryQuizDto.buildFromEntity(quiz);

        assertEquals(1L, quizDto.getId());
        assertEquals("ACTIVE", quizDto.getStatus());
        assertEquals(0, quizDto.getMistakes());
        assertEquals("2022-04-12T12:00:00", quizDto.getStartDateTime());
    }

    @Test
    void testBuildFromDto() {

        QuizDto quizDto = new QuizDto();
        quizDto.setId(1L);
        quizDto.setStatus("ACTIVE");
        quizDto.setMistakes(0);
        quizDto.setStartDateTime("2022-04-12T12:00:00");

        FactoryQuizDto<QuizDto, Quiz> factoryQuizDto = new FactoryQuizDto<>(dateUtil);
        when(dateUtil.stringToLocalDateTime("2022-04-12T12:00:00")).thenReturn(LocalDateTime.now());
        Quiz quiz = factoryQuizDto.buildFromDto(quizDto);

        assertEquals(1L, quiz.getId());
        assertEquals(StatusQuiz.ACTIVE, quiz.getStatus());
        assertEquals(0, quiz.getMistakes());

    }

}
