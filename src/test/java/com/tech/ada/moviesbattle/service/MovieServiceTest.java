package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.model.Movie;
import com.tech.ada.moviesbattle.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void testFindRandomMovies() {
        // Mock
        List<Movie> movies = Arrays.asList(new Movie("Dune","2021","tt1160419"),
                new Movie("Joker","2019","tt7286456"),
                new Movie("Inception","2010","tt1375666"));
        int quantity = 3;

        when(movieRepository.findRandomMovies(quantity)).thenReturn(movies);

        List<Movie> result = movieService.getMovies(quantity);
        verify(movieRepository, times(1)).findRandomMovies(quantity);
        assertEquals(movies, result);
    }

    @Test
    public void testCount() {
        long expectedCount = 10;
        when(movieRepository.count()).thenReturn(expectedCount);
        long result = movieRepository.count();
        verify(movieRepository, times(1)).count();
        assertEquals(expectedCount, result);
    }
}
