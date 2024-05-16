package com.tech.ada.moviesbattle.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.tech.ada.moviesbattle.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindRandomMovies() {
        List<Movie> randomMovies = movieRepository.findRandomMovies(2);
        assertThat(randomMovies).isNotNull();
        assertThat(randomMovies).hasSize(2);
    }

    @Test
    public void testCount() {
        long movieCount = movieRepository.count();

        assertThat(movieCount).isEqualTo(20);
    }

}