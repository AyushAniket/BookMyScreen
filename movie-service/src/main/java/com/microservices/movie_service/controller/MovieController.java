package com.microservices.movie_service.controller;

import com.microservices.movie_service.dto.MovieRequest;
import com.microservices.movie_service.dto.MovieResponse;
import com.microservices.movie_service.model.Movie;
import com.microservices.movie_service.service.MovieService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="movie", fallbackMethod = "fallback")
    @Retry(name="movie")
    public Movie addMovie(@RequestBody MovieRequest movieRequest) {
        return movieService.addMovie(movieRequest);
    }

    private Movie fallback(MovieRequest movieRequest, RuntimeException runtimeException) throws RuntimeException {
        return null;
    }

    @GetMapping("/displayingMovies")
    public List<MovieResponse> getAllDisplayingMovies() {
        return movieService.getAllDisplayingMovies();
    }

    @GetMapping("/comingSoonMovies")
    public List<MovieResponse> getAllComingSoonMovies() {
        return movieService.getAllComingSoonMovies();
    }
    @GetMapping("{movieId}")
    public MovieResponse getMovieById(@PathVariable int movieId) {
        return movieService.getMovieByMovieId(movieId);
    }
}
