package com.microservices.movie_service.controller;

import com.microservices.movie_service.model.MovieTheaterTime;
import com.microservices.movie_service.service.MovieTheaterTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie/movieTheaterTimes/")
@RequiredArgsConstructor
public class MovieTheaterTimeController {
    private final MovieTheaterTimeService movieTheaterTimeService;

    @GetMapping("getMovieTheaterTimeTheaterAndMovieId/{theaterId}/{movieId}")
    public List<MovieTheaterTime> getMovieTheaterTimeTheaterAndMovieId(@PathVariable int theaterId,
                                                                       @PathVariable int movieId) {
        return movieTheaterTimeService.getMovieTheaterTimeTheaterAndMovieId(theaterId, movieId);
    }
}
