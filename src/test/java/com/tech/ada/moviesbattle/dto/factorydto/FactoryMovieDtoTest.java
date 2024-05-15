package com.tech.ada.moviesbattle.dto.factorydto;

import com.tech.ada.moviesbattle.model.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.tech.ada.moviesbattle.dto.MovieDto;

public class FactoryMovieDtoTest {

    @Test
    void testBuildFromEntity() {
        Movie movie = new Movie();
        movie.setYear("2022");
        movie.setTitle("Inception");
        movie.setImdbId("tt1375666");

        FactoryMovieDto<MovieDto, Movie> factory = new FactoryMovieDto<>();
        MovieDto movieDto = factory.buildFromEntity(movie);

        assertEquals("2022", movieDto.getYear());
        assertEquals("Inception", movieDto.getTitle());
        assertEquals("tt1375666", movieDto.getImdbId());
    }

    @Test
    void testBuildFromDto() {
        MovieDto movieDto = new MovieDto();
        movieDto.setYear("2022");
        movieDto.setTitle("Inception");
        movieDto.setImdbId("tt1375666");

        FactoryMovieDto<MovieDto, Movie> factory = new FactoryMovieDto<>();
        Movie movie = factory.buildFromDto(movieDto);

        assertEquals("2022", movie.getYear());
        assertEquals("Inception", movie.getTitle());
        assertEquals("tt1375666", movie.getImdbId());
    }
}
