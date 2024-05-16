package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.dto.AnswerDto;
import com.tech.ada.moviesbattle.dto.QuestionDto;
import com.tech.ada.moviesbattle.dto.factorydto.FactoryQuestionDto;
import com.tech.ada.moviesbattle.exception.NotExistMoviesException;
import com.tech.ada.moviesbattle.model.*;
import com.tech.ada.moviesbattle.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The QuestionService class acts as a service layer component responsible for managing question-related operations
 * and interactions with the underlying data store.
 */
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MovieService movieService;
    private final FactoryQuestionDto<QuestionDto, Question> factoryQuestionDto;
    private final PlayerService playerService;
    private final QuizService quizService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, MovieService movieService,
                           FactoryQuestionDto<QuestionDto, Question> factoryQuestionDto,
                           PlayerService playerService, QuizService quizService) {
        this.questionRepository = questionRepository;
        this.movieService = movieService;
        this.factoryQuestionDto = factoryQuestionDto;
        this.playerService = playerService;
        this.quizService = quizService;
    }

    /**
     * Creates a new question for the specified quiz and converts it into a QuestionDto object.
     * @param quiz
     * @return
     * @throws Exception
     */
    public QuestionDto createQuestionAndConvertDto(Quiz quiz) throws Exception {

        Optional<Question> optionalQuestion = this.questionRepository.findUnansweredByPlayer(quiz);
        if (optionalQuestion.isPresent()) {
            return this.factoryQuestionDto.buildFromEntity(optionalQuestion.get());
        }

        Question question = createQuestion(quiz);
        return this.factoryQuestionDto.buildFromEntity(question);
    }

    /**
     * Creates a new question for the specified quiz.
     * @param quiz
     * @return
     * @throws NotExistMoviesException
     */
    public Question createQuestion(Quiz quiz) throws NotExistMoviesException {
        List<Movie> movies = getMovies(quiz);

        Question question = new Question();
        question.setMovieList(movies);
        question.setQuiz(quiz);
        question.setStatus(StatusQuestion.UNANSWERED);
        return questionRepository.save(question);
    }

    /**
     * Retrieves a list of movies for a question based on the specified quiz.
     * @param quiz
     * @return
     * @throws NotExistMoviesException
     */
    public List<Movie> getMovies(Quiz quiz) throws NotExistMoviesException {
        List<Movie> movies;
        int controllerToScape = 0;
        do {
            movies = movieService.getMovies(2);
            controllerToScape++;
            if (controllerToScape > movieService.countElements()) {
                throw new NotExistMoviesException("Not exist movies to create new questions.");
            }
        } while (this.quizService.thereIsQuestionWithSameMovies(quiz, movies));

        return movies;
    }

    /**
     * Validates the answer submitted by a player for the specified quiz.
     * @param player
     * @param dto
     * @param quiz
     * @return
     */
    public boolean validateAnswerQuestion(Player player, AnswerDto dto, Quiz quiz) {
        Optional<Question> optionalQuestion = this.questionRepository.findUnansweredByPlayer(quiz);
        if (optionalQuestion.isEmpty()) {
            throw new NoSuchElementException("Question not found.");
        }

        String dtoImdbIdMovie = dto.getImdbId();
        Optional<Movie> movieChosen = optionalQuestion.get()
                .getMovieList()
                .stream()
                .filter(movie -> movie.getImdbId().equals(dtoImdbIdMovie))
                .findFirst();

        if (movieChosen.isEmpty()) {
            throw new NoSuchElementException("Movie not found in list");
        }

        Optional<Movie> movieHighestScore = optionalQuestion.get()
                .getMovieList()
                .stream()
                .max(Comparator.comparingDouble(Movie::calculatorPoints));

        if (movieHighestScore.isEmpty()) {
            throw new NoSuchElementException("Movie not found in movie Highest Score");
        }

        Question question = optionalQuestion.get();
        question.setStatus(StatusQuestion.ANSWERED);
        this.questionRepository.save(question);

        if (movieHighestScore.get().getImdbId().equals(movieChosen.get().getImdbId())) {
            player.setScore(player.getScore() + 1);
            this.playerService.updatePlayer(player);
            return true;
        } else {
            quiz.setMistakes(quiz.getMistakes() + 1);
            this.quizService.updateQuizCheckMistakes(quiz);
            return false;
        }
    }
}
