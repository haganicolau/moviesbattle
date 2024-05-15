package com.tech.ada.moviesbattle.dto.factorydto;

import com.tech.ada.moviesbattle.dto.MovieDto;
import com.tech.ada.moviesbattle.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class FactoryMovieDto <T, F> implements IFactoryDto<MovieDto, Movie> {

    @Override
    public MovieDto buildFromEntity(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setYear(movie.getYear());
        movieDto.setTitle(movie.getTitle());
        movieDto.setImdbId(movie.getImdbId());
        return movieDto;
    }

    @Override
    public Movie buildFromDto(MovieDto dto) {
        Movie movie = new Movie();
        movie.setYear(dto.getYear());
        movie.setTitle(dto.getTitle());
        movie.setImdbId(dto.getImdbId());
        return movie;
    }

}
