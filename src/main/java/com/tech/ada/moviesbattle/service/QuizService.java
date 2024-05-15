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

    public Optional<QuizDto> getActiveQuiz(Player player) {
        Optional<Quiz> activeQuiz = quizRepository.findActiveQuizByPlayer(player);
        return activeQuiz.map(quiz -> this.factoryQuizDto.buildFromEntity(quiz));
    }

    public QuizDto checkIfNoActiveQuizCreateOne(Player player) {
        Optional<QuizDto> activeQuiz = getActiveQuiz(player);

        return activeQuiz.orElseGet(() -> createNewQuiz(player));

    }

    public QuizDto createNewQuiz(Player player) {
        Quiz quiz = quizRepository.save(new Quiz(
            LocalDateTime.now(),
            player,
            StatusQuiz.ACTIVE,
            0
        ));

        return this.factoryQuizDto.buildFromEntity(quiz);
    }

    public Optional<Quiz> getActiveQuizById(Player player, Long id) {
        return quizRepository.findActiveQuiz(player, id);
    }

    public void updateQuizCheckMistakes(Quiz quiz) {
        if (quiz.getMistakes() >= 3) {
            quiz.setStatus(StatusQuiz.INACTIVE);
        }
        this.quizRepository.save(quiz);
    }

    public boolean thereIsQuestionWithSameMovies(Quiz quiz, List<Movie> movies) {
        return quiz.getQuestionList().stream()
                .filter(question -> question.getMovieList().size() == movies.size())
                .anyMatch(question -> question.getMovieList().containsAll(movies));
    }
}
