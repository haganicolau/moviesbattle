package com.tech.ada.moviesbattle.controller;

import com.tech.ada.moviesbattle.dto.QuizDto;
import com.tech.ada.moviesbattle.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tech.ada.moviesbattle.service.PlayerService;
import com.tech.ada.moviesbattle.service.QuizService;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@RestController
@RequestMapping()
public class QuizController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(QuizController.class);

    private PlayerService playerService;
    private QuizService quizService;

    @Autowired
    public QuizController(PlayerService playerService, QuizService quizService) {
        this.playerService = playerService;
        this.quizService = quizService;
    }

    @GetMapping(value = "/battlemovies/quiz", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getQuiz() {
        LOG.info("Get active Quiz");

        try {
            this.authentication = SecurityContextHolder.getContext().getAuthentication();;
            if (authentication == null) {
                return getUnauthorized();
            }

            Player player = playerService.getPlayerByUsername(authentication.getName());
            if (player == null) {
                return getUnauthorized();
            }

            Optional<QuizDto>  optionalQuizDto = this.quizService.getActiveQuiz(player);
            if (optionalQuizDto.isPresent()) {
                return ResponseEntity.ok().body(optionalQuizDto.get());
            }

            return getNotFound();
        } catch (Exception ex) {
            String message = String.format("Error while retrieving quiz. Error: %s", ex.getMessage());
            LOG.error(message, ex);
            return getErrorMessage("Error while retrieving Quiz. " + message);
        }
    }

    @PostMapping(value = "/battlemovies/quiz/start", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuiz() {
        LOG.info("Create a new Quiz");

        try {
            this.authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return getUnauthorized();
            }

            Player player = playerService.getPlayerByUsername(authentication.getName());
            if (player == null) {
                return getUnauthorized();
            }

            QuizDto dto = this.quizService.checkIfNoActiveQuizCreateOne(player);
            return ResponseEntity.ok().body(dto);

        } catch (Exception ex) {
            String message = String.format("Error while creating a quiz. Error: %s", ex.getMessage());
            LOG.error(message, ex);
            return getErrorMessage(message);
        }
    }
}
