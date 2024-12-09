package com.microservices.movie_service.repository;

import com.microservices.movie_service.model.MovieTheaterTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieTheaterTimeRepository extends JpaRepository<MovieTheaterTime, Integer> {
    List<MovieTheaterTime> getMovieTheaterTimeByTheaterTheaterIdAndMovieMovieId(int theaterId, int movieId);
}

