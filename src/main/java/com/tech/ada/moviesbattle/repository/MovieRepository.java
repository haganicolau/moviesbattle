package com.tech.ada.moviesbattle.repository;

import com.tech.ada.moviesbattle.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movies ORDER BY RAND() LIMIT :quantity", nativeQuery = true)
    List<Movie> findRandomMovies(@Param("quantity") int quantity);

    long count();
}
