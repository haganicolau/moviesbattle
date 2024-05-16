package com.tech.ada.moviesbattle.dto.factorydto;

import com.tech.ada.moviesbattle.dto.MovieDto;
import com.tech.ada.moviesbattle.dto.QuestionDto;
import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.model.*;
import com.tech.ada.moviesbattle.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactoryQuestionDtoTest {

    @Mock
    private DateUtil dateUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuildFromEntity() {
        Question question = new Question();
        question.setId(1L);
        question.setStatus(StatusQuestion.ANSWERED);

        Quiz quiz = new Quiz();
        quiz.setId(1L);
        question.setQuiz(quiz);
        LocalDateTime startDateTime = LocalDateTime.now();
        quiz.setStartDateTime(startDateTime);
        quiz.setStatus(StatusQuiz.ACTIVE);

        List<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setTitle("Inception");
        movie1.setYear("2010");
        movie1.setImdbId("tt1375666");
        movies.add(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Interstellar");
        movie2.setYear("2014");
        movie2.setImdbId("tt0816692");
        movies.add(movie2);

        question.setMovieList(movies);

        FactoryMovieDto<MovieDto, Movie> factoryMovieDto = new FactoryMovieDto<>();
        FactoryQuizDto<QuizDto, Quiz> factoryQuizDto = new FactoryQuizDto<QuizDto, Quiz>(dateUtil);
        FactoryQuestionDto<QuestionDto, Question> factoryQuestionDto = new FactoryQuestionDto<>(factoryMovieDto, factoryQuizDto);

        QuestionDto questionDto = factoryQuestionDto.buildFromEntity(question);

        assertEquals(1L, questionDto.getQuestionId());
        assertEquals("ANSWERED", questionDto.getStatus());
        assertEquals(1L, questionDto.getQuiz().getId());
        assertEquals(2, questionDto.getMovies().size());
        assertEquals("Inception", questionDto.getMovies().get(0).getTitle());
        assertEquals("2010", questionDto.getMovies().get(0).getYear());
        assertEquals("tt1375666", questionDto.getMovies().get(0).getImdbId());
        assertEquals("Interstellar", questionDto.getMovies().get(1).getTitle());
        assertEquals("2014", questionDto.getMovies().get(1).getYear());
        assertEquals("tt0816692", questionDto.getMovies().get(1).getImdbId());
    }

    @Test
    void testBuildFromDto() {

        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(1L);
        questionDto.setStatus("ANSWERED");

        QuizDto quizDto = new QuizDto();
        quizDto.setId(1L);
        quizDto.setStatus("ACTIVE");

        questionDto.setQuiz(quizDto);
        List<MovieDto> movieDtos = new ArrayList<>();
        MovieDto movieDto1 = new MovieDto();
        movieDto1.setTitle("Inception");
        movieDto1.setYear("2010");
        movieDto1.setImdbId("tt1375666");
        MovieDto movieDto2 = new MovieDto();
        movieDto2.setTitle("Interstellar");
        movieDto2.setYear("2014");
        movieDto2.setImdbId("tt0816692");
        movieDtos.add(movieDto1);
        movieDtos.add(movieDto2);
        questionDto.setMovies(movieDtos);

        FactoryMovieDto<MovieDto, Movie> factoryMovieDto = new FactoryMovieDto<>();
        FactoryQuizDto<QuizDto, Quiz> factoryQuizDto = new FactoryQuizDto<>(dateUtil);
        FactoryQuestionDto<QuestionDto, Question> factoryQuestionDto = new FactoryQuestionDto<>(factoryMovieDto, factoryQuizDto);

        Question question = factoryQuestionDto.buildFromDto(questionDto);

        assertEquals(StatusQuestion.ANSWERED, question.getStatus());
        assertEquals(1L, question.getQuiz().getId());
        assertEquals(2, question.getMovieList().size());
        assertEquals("Inception", question.getMovieList().get(0).getTitle());
        assertEquals("2010", question.getMovieList().get(0).getYear());
        assertEquals("tt1375666", question.getMovieList().get(0).getImdbId());
        assertEquals("Interstellar", question.getMovieList().get(1).getTitle());
        assertEquals("2014", question.getMovieList().get(1).getYear());
        assertEquals("tt0816692", question.getMovieList().get(1).getImdbId());
    }
}