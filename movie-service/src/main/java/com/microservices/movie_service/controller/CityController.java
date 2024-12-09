package com.microservices.movie_service.controller;

import com.microservices.movie_service.dto.CityRequest;
import com.microservices.movie_service.model.City;
import com.microservices.movie_service.service.CityService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/cities/")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping("getCitiesByMovieId/{movieId}")
    public List<City> getCitiesByMovieId(@PathVariable int movieId) {
        return cityService.getCitiesByMovieId(movieId);
    }

    @GetMapping("getAll")
    public List<City> getAll() {
        return cityService.getAll();
    }

    @PostMapping("add")
    @CircuitBreaker(name="city", fallbackMethod = "fallback")
    @Retry(name="city")
    public void add(@RequestBody CityRequest cityRequest) {
        cityService.add(cityRequest);
    }
    private void fallback(CityRequest cityRequest, RuntimeException runtimeException) { }
}
