package com.tech.ada.moviesbattle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    @Test
    public void testToString() {
        Movie movie = new Movie();
        movie.setTitle("The Shawshank Redemption");
        movie.setYear("1994");
        movie.setImdbId("tt0111161");
        movie.setImdbRating(9.3);
        movie.setImdbVotes(2345678);

        Movie movie2 = new Movie();
        movie2.setTitle("The Shawshank Redemption");
        movie2.setYear("1994");
        movie2.setImdbId("tt0111161");
        movie2.setImdbRating(9.3);
        movie2.setImdbVotes(2345678);

        String expected = "Movie{id=null, title='The Shawshank Redemption', year='1994', imdbId='tt0111161', imdbRating=9.3, imdbVotes=2345678, questionList=null}";
        assertEquals(expected, movie.toString());
        assertEquals(movie.hashCode(), movie2.hashCode());
    }

    @Test
    public void testEquals() {
        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setTitle("Inception");

        Movie movie2 = new Movie();
        movie2.setId(1L);
        movie2.setTitle("Inception");

        Movie movie3 = new Movie();
        movie3.setId(2L);
        movie3.setTitle("The Dark Knight");

        assertEquals(movie1, movie2);
        assertEquals(movie1.hashCode(), movie2.hashCode());
        assertNotEquals(movie1, movie3);
        assertNotEquals(movie1.hashCode(), movie3.hashCode());
    }
}
