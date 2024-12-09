package com.microservices.movie_service.controller;

import com.microservices.movie_service.dto.ImageRequest;
import com.microservices.movie_service.service.MovieImageService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movie/images/")
@RequiredArgsConstructor
public class MovieImageController {
    private final MovieImageService movieImageService;

    @PostMapping("add")
    @CircuitBreaker(name = "image", fallbackMethod = "fallback")
    @Retry(name = "image")
    public void add(@RequestBody ImageRequest imageRequest) {
        movieImageService.addMovieImage(imageRequest);
    }

    private void fallback(ImageRequest imageRequest, RuntimeException runtimeException) { }

}
