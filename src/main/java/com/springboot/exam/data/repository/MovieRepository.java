package com.springboot.exam.data.repository;

import com.springboot.exam.data.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m FROM Movie m WHERE m.MovieName LIKE %:name%")
    List<Movie> findMoviesByNameContaining(@Param("name") String name);

//    Optional<Movie> findByMovieName(String name);

}
