package com.tech.ada.moviesbattle.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuizControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetQuizEndpoint_NotFound() {

        String username = "user1";
        String password = "password";
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/battlemovies/quiz",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testGetQuizEndpoint_UNAUTHORIZED() {

        String username = "";
        String password = "";
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/battlemovies/quiz",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class
        );

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    public void testStartQuizEndpoint() {
        String username = "user1";
        String password = "password";
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/battlemovies/quiz/start",
                HttpMethod.POST,
                new HttpEntity<>(headers),
                String.class
        );

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

