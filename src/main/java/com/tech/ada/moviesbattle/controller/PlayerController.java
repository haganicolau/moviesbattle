package com.tech.ada.moviesbattle.controller;

import com.tech.ada.moviesbattle.dto.ScoreDto;
import com.tech.ada.moviesbattle.model.Player;
import com.tech.ada.moviesbattle.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class PlayerController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerController.class);
    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/battlemovies/score", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getScore() {
        LOG.info("Get Scores");

        try {
            this.authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return getUnauthorized();
            }

            Player player = playerService.getPlayerByUsername(authentication.getName());
            if (player == null) {
                return getUnauthorized();
            }

            ScoreDto dto = this.playerService.getScore();
            return ResponseEntity.ok().body(dto);

        } catch (Exception ex) {
            String message = String.format("Error while retrieving quiz. Error: %s", ex.getMessage());
            LOG.error(message, ex);
            return getErrorMessage("Error while retrieving Quiz. " + message);
        }
    }
}
