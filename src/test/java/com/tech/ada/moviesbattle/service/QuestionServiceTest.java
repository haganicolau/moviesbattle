package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.dto.MovieDto;
import com.tech.ada.moviesbattle.dto.QuestionDto;
import com.tech.ada.moviesbattle.dto.factorydto.FactoryQuestionDto;
import com.tech.ada.moviesbattle.exception.NotExistMoviesException;
import com.tech.ada.moviesbattle.model.*;
import com.tech.ada.moviesbattle.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private MovieService movieService;

    @Mock
    private FactoryQuestionDto<QuestionDto, Question> factoryQuestionDto;

    @Mock
    private PlayerService playerService;

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createQuestionAndConvertDto_ShouldReturnQuestionDto_WhenUnansweredQuestionExists() throws Exception {

        Quiz quiz = new Quiz();
        Question question = new Question();
        when(questionRepository.findUnansweredByPlayer(quiz)).thenReturn(Optional.of(question));
        QuestionDto expectedDto = new QuestionDto();
        when(factoryQuestionDto.buildFromEntity(question)).thenReturn(expectedDto);

        QuestionDto result = questionService.createQuestionAndConvertDto(quiz);
        assertNotNull(result);
        assertSame(expectedDto, result);
    }

    @Test
    void createQuestion_ShouldThrowNotExistMoviesException_WhenNoMoviesExist() {
        Quiz quiz = new Quiz();
        when(movieService.getMovies(2)).thenThrow(NotExistMoviesException.class);

        assertThrows(NotExistMoviesException.class, () -> questionService.createQuestion(quiz));
    }

    @Test
    void createQuestion_ShouldCreateQuestionWithMoviesAndReturnIt() throws Exception {
        Quiz quiz = new Quiz();
        Player player = new Player();
        player.setId(1L);
        player.setUsername("user1");

        quiz.setId(1L);
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setPlayer(player);

        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setTitle("Captain America: The First Avenger");
        movie1.setImdbId("tt0458339");
        movie1.setImdbRating(6.9);
        movie1.setImdbVotes(896467);
        movie1.setYear("2011");

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setTitle("Shazam");
        movie2.setImdbId("tt0448115");
        movie2.setYear("2019");
        movie2.setImdbRating(7.0);
        movie2.setImdbVotes(384909);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        Question newQuestion = new Question();
        newQuestion.setMovieList(movies);
        newQuestion.setStatus(StatusQuestion.UNANSWERED);
        newQuestion.setQuiz(quiz);

        QuestionDto expectedDto = new QuestionDto();
        List<MovieDto> movieDtos = new ArrayList<>();
        MovieDto movieDto1 = new MovieDto();
        movieDto1.setTitle("Captain America: The First Avenger");
        movieDto1.setImdbId("tt0458339");
        movieDto1.setYear("2011");
        movieDtos.add(movieDto1);

        MovieDto movieDto2 = new MovieDto();
        movieDto2.setTitle("Shazam");
        movieDto2.setImdbId("tt0448115");
        movieDto2.setYear("2019");
        movieDtos.add(movieDto2);

        expectedDto.setMovies(movieDtos);
        expectedDto.setStatus("UNANSWERED");

        when(questionRepository.findUnansweredByPlayer(quiz)).thenReturn(Optional.empty());
        when(questionRepository.save(any(Question.class))).thenReturn(newQuestion);

        when(movieService.getMovies(2)).thenReturn(movies);
        when(movieService.countElements()).thenReturn(20L);
        when(quizService.thereIsQuestionWithSameMovies(quiz, movies)).thenReturn(false);
        when(factoryQuestionDto.buildFromEntity(newQuestion)).thenReturn(expectedDto);

        QuestionDto result = questionService.createQuestionAndConvertDto(quiz);

        assertNotNull(result);
        assertSame(expectedDto, result);
    }

    @Test
    void createQuestion_findUnansweredByPlayer() throws Exception {
        Quiz quiz = new Quiz();
        Player player = new Player();
        player.setId(1L);
        player.setUsername("user1");

        quiz.setId(1L);
        quiz.setStatus(StatusQuiz.ACTIVE);
        quiz.setPlayer(player);

        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setTitle("Captain America: The First Avenger");
        movie1.setImdbId("tt0458339");
        movie1.setImdbRating(6.9);
        movie1.setImdbVotes(896467);
        movie1.setYear("2011");

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setTitle("Shazam");
        movie2.setImdbId("tt0448115");
        movie2.setYear("2019");
        movie2.setImdbRating(7.0);
        movie2.setImdbVotes(384909);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);

        Question newQuestion = new Question();
        newQuestion.setMovieList(movies);
        newQuestion.setStatus(StatusQuestion.UNANSWERED);
        newQuestion.setQuiz(quiz);

        QuestionDto expectedDto = new QuestionDto();
        List<MovieDto> movieDtos = new ArrayList<>();
        MovieDto movieDto1 = new MovieDto();
        movieDto1.setTitle("Captain America: The First Avenger");
        movieDto1.setImdbId("tt0458339");
        movieDto1.setYear("2011");
        movieDtos.add(movieDto1);

        MovieDto movieDto2 = new MovieDto();
        movieDto2.setTitle("Shazam");
        movieDto2.setImdbId("tt0448115");
        movieDto2.setYear("2019");
        movieDtos.add(movieDto2);

        expectedDto.setMovies(movieDtos);
        expectedDto.setStatus("UNANSWERED");

        when(questionRepository.findUnansweredByPlayer(quiz)).thenReturn(Optional.of(newQuestion));
        when(factoryQuestionDto.buildFromEntity(newQuestion)).thenReturn(expectedDto);

        QuestionDto result = questionService.createQuestionAndConvertDto(quiz);

        assertNotNull(result);
        assertSame(expectedDto, result);
    }

}
