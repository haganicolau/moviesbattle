package com.tech.ada.moviesbattle.dto.factorydto;

import com.tech.ada.moviesbattle.dto.MovieDto;
import com.tech.ada.moviesbattle.model.Movie;
import org.springframework.stereotype.Component;

/**
 * The FactoryMovieDto class is a Spring component responsible for creating MovieDto objects from Movie entities and
 * vice versa.
 * @param <T>
 * @param <F>
 */
@Component
public class FactoryMovieDto <T, F> implements IFactoryDto<MovieDto, Movie> {

    /**
     * Implements the buildFromEntity method defined in the IFactoryDto interface. It receives an entity and returns
     * a dto
     * @param movie
     * @return
     */
    @Override
    public MovieDto buildFromEntity(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setYear(movie.getYear());
        movieDto.setTitle(movie.getTitle());
        movieDto.setImdbId(movie.getImdbId());
        return movieDto;
    }

    /**
     * Implements the buildFromDto method defined in the IFactoryDto interface. It receives a dto and returns
     * an entity.
     * @param dto
     * @return
     */
    @Override
    public Movie buildFromDto(MovieDto dto) {
        Movie movie = new Movie();
        movie.setYear(dto.getYear());
        movie.setTitle(dto.getTitle());
        movie.setImdbId(dto.getImdbId());
        return movie;
    }

}
