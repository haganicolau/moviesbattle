package com.tech.ada.moviesbattle.controller;

import com.tech.ada.moviesbattle.dto.AnswerDto;
import com.tech.ada.moviesbattle.dto.QuestionDto;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.model.Quiz;
import com.tech.ada.moviesbattle.service.PlayerService;
import com.tech.ada.moviesbattle.service.QuestionService;
import com.tech.ada.moviesbattle.service.QuizService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping()
public class QuestionController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
    private PlayerService playerService;
    private QuizService quizService;
    private QuestionService questionService;

    @Autowired
    public QuestionController(PlayerService playerService, QuizService quizService, QuestionService questionService) {
        this.playerService = playerService;
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping(value = "/battlemovies/{idQuiz}/question", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuestion(@PathVariable Long idQuiz) {
        LOG.info("Create new question from idQuiz: {}", idQuiz);

        try {
            this.authentication = SecurityContextHolder.getContext().getAuthentication();
            Player player = playerService.getPlayerByUsername(authentication.getName());
            if (player == null) {
                return getUnauthorized();
            }

            Optional<Quiz> quizOptional = quizService.getActiveQuizById(player, idQuiz);
            if (quizOptional.isEmpty()) {
                return getNotFound();
            }

            QuestionDto dto =  this.questionService.createQuestionAndConvertDto(quizOptional.get());
            return ResponseEntity.ok().body(dto);
        } catch (Exception ex) {
            String message = String.format("Error while creating a question. Error: %s", ex.getMessage());
            LOG.error(message, ex);
            return getErrorMessage(message);
        }

    }

    @PostMapping(value ="/battlemovies/{idQuiz}/answer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> answerQuestion(@PathVariable Long idQuiz, @Valid @RequestBody AnswerDto dto) {
        LOG.info("Answer the question from idQuiz: {}", idQuiz);

        try {
            this.authentication = SecurityContextHolder.getContext().getAuthentication();
            Player player = playerService.getPlayerByUsername(authentication.getName());
            if (player == null) {
                return getUnauthorized();
            }

            Optional<Quiz> quizOptional = quizService.getActiveQuizById(player, idQuiz);
            if (quizOptional.isEmpty()) {
                return getNotFound();
            }

            boolean answer = this.questionService.validateAnswerQuestion(player, dto, quizOptional.get());
            if (answer) {
                return getSuccessMessage("Right answer! You earned 1 point");
            }

            return getSuccessMessage("Wrong answer! You noted 1 mistake.");

        } catch (Exception ex) {
            String message = String.format("Error while answering the question. Error: %s", ex.getMessage());
            LOG.error(message, ex);
            return getErrorMessage("Error while answering the question. " + message);
        }
    }

}
