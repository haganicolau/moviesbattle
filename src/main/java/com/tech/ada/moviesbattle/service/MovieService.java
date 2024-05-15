package com.tech.ada.moviesbattle.service;

import com.tech.ada.moviesbattle.model.Movie;
import com.tech.ada.moviesbattle.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(int quantity) {
        return this.movieRepository.findRandomMovies(quantity);
    }

    public long countElements() {
        return this.movieRepository.count();
    }

}
