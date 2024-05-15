package com.tech.ada.moviesbattle.dto;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class QuestionDtoTest {

    @Test
    void testToString() {
        QuizDto quizDto = new QuizDto();
        //quizDto.setQuizId(1L);

        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Title");
        movieDto.setYear("2022");
        movieDto.setImdbId("tt1234567");

        List<MovieDto> movies = new ArrayList<>();
        movies.add(movieDto);

        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuiz(quizDto);
        questionDto.setMovies(movies);
        questionDto.setStatus("Active");
        questionDto.setQuestionId(1L);

        String expected = "QuestionDto{quiz=QuizDto{startDateTime='null', status='null', id=null, mistakes=0}, movies=[MovieDto{title='Title', year='2022', imdbId='tt1234567'}], status='Active', questionId=1}";
        assertEquals(expected, questionDto.toString());
    }

    @Test
    void testEquals() {
        QuizDto quizDto = new QuizDto();
        //quizDto.setQuizId(1L);

        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Title");
        movieDto.setYear("2022");
        movieDto.setImdbId("tt1234567");

        List<MovieDto> movies = new ArrayList<>();
        movies.add(movieDto);

        QuestionDto questionDto1 = new QuestionDto();
        questionDto1.setQuiz(quizDto);
        questionDto1.setMovies(movies);
        questionDto1.setStatus("Active");
        questionDto1.setQuestionId(1L);

        QuestionDto questionDto2 = new QuestionDto();
        questionDto2.setQuiz(quizDto);
        questionDto2.setMovies(movies);
        questionDto2.setStatus("Active");
        questionDto2.setQuestionId(1L);

        QuestionDto questionDto3 = new QuestionDto();
        questionDto3.setQuiz(quizDto);
        questionDto3.setMovies(movies);
        questionDto3.setStatus("Inactive");
        questionDto3.setQuestionId(2L);

        assertEquals(questionDto1, questionDto2);
        assertNotEquals(questionDto1, questionDto3);
    }
}
