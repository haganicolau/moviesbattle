package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.model.Movie;
import com.tech.ada.moviesbattle.model.StatusQuiz;
import com.tech.ada.moviesbattle.repository.QuizRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.dto.factorydto.FactoryQuizDto;
import com.tech.ada.moviesbattle.dto.QuizDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * The QuizService class acts as a service layer component responsible for managing quiz-related operations and
 * interactions with the underlying data store.
 */
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final FactoryQuizDto<QuizDto, Quiz> factoryQuizDto;
    private static final Logger LOG = LoggerFactory.getLogger(QuizService.class);

    @Autowired
    public QuizService(QuizRepository quizRepository, FactoryQuizDto<QuizDto, Quiz> factoryQuizDto) {
        this.quizRepository = quizRepository;
        this.factoryQuizDto = factoryQuizDto;
    }

    /**
     * Retrieves the active quiz for the specified player.
     * @param player
     * @return
     */
    public Optional<QuizDto> getActiveQuiz(Player player) {
        Optional<Quiz> activeQuiz = quizRepository.findActiveQuizByPlayer(player);
        return activeQuiz.map(quiz -> this.factoryQuizDto.buildFromEntity(quiz));
    }

    /**
     * Checks if the player has an active quiz; if not, creates a new one.
     * @param player
     * @return
     */
    public QuizDto checkIfNoActiveQuizCreateOne(Player player) {
        Optional<QuizDto> activeQuiz = getActiveQuiz(player);

        return activeQuiz.orElseGet(() -> createNewQuiz(player));

    }

    /**
     * Creates a new quiz for the specified player.
     * @param player
     * @return
     */
    public QuizDto createNewQuiz(Player player) {
        Quiz quiz = quizRepository.save(new Quiz(
            LocalDateTime.now(),
            player,
            StatusQuiz.ACTIVE,
            0
        ));

        return this.factoryQuizDto.buildFromEntity(quiz);
    }

    /**
     * Retrieves the active quiz for the specified player by ID.
     * @param player
     * @param id
     * @return
     */
    public Optional<Quiz> getActiveQuizById(Player player, Long id) {
        return quizRepository.findActiveQuiz(player, id);
    }

    /**
     * Updates the quiz status based on the number of mistakes.
     * @param quiz
     */
    public void updateQuizCheckMistakes(Quiz quiz) {
        if (quiz.getMistakes() >= 3) {
            quiz.setStatus(StatusQuiz.INACTIVE);
        }
        this.quizRepository.save(quiz);
    }

    /**
     * Checks if there is a question in the quiz with the same movies as the provided list.
     * @param quiz
     * @param movies
     * @return
     */
    public boolean thereIsQuestionWithSameMovies(Quiz quiz, List<Movie> movies) {
        return quiz.getQuestionList().stream()
                .filter(question -> question.getMovieList().size() == movies.size())
                .anyMatch(question -> question.getMovieList().containsAll(movies));
    }
}
