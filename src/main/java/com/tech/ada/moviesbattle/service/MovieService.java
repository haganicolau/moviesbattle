package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.model.Movie;
import com.tech.ada.moviesbattle.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The MovieService class serves as a service layer component responsible for handling movie-related operations and
 * interactions with the underlying data store.
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Retrieves a list of random movies from the database.
     * @param quantity
     * @return
     */
    public List<Movie> getMovies(int quantity) {
        return this.movieRepository.findRandomMovies(quantity);
    }

    /**
     * Counts the total number of movies in the database.
     * @return
     */
    public long countElements() {
        return this.movieRepository.count();
    }

}
