package com.microservices.movie_service.service;

import com.microservices.movie_service.model.MovieTheaterTime;
import com.microservices.movie_service.repository.MovieTheaterTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieTheaterTimeService {
    private final MovieTheaterTimeRepository movieTheaterTimeRepository;

    public List<MovieTheaterTime> getMovieTheaterTimeTheaterAndMovieId(int theaterId, int movieId) {
        return movieTheaterTimeRepository.getMovieTheaterTimeByTheaterTheaterIdAndMovieMovieId(theaterId, movieId);
    }
}
