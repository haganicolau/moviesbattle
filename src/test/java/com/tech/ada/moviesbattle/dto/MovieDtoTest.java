package com.tech.ada.moviesbattle.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MovieDtoTest {

    @Test
    void testToString() {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle("Title");
        movieDto.setYear("2022");
        movieDto.setImdbId("tt1234567");

        String expected = "MovieDto{title='Title', year='2022', imdbId='tt1234567'}";
        assertEquals(expected, movieDto.toString());
    }

    @Test
    void testEquals() {
        MovieDto movieDto1 = new MovieDto();
        movieDto1.setTitle("Title");
        movieDto1.setYear("2022");
        movieDto1.setImdbId("tt1234567");

        MovieDto movieDto2 = new MovieDto();
        movieDto2.setTitle("Title");
        movieDto2.setYear("2022");
        movieDto2.setImdbId("tt1234567");

        MovieDto movieDto3 = new MovieDto();
        movieDto3.setTitle("Title");
        movieDto3.setYear("2021");
        movieDto3.setImdbId("tt1234567");

        assertEquals(movieDto1, movieDto2);
        assertNotEquals(movieDto1, movieDto3);
    }
}
