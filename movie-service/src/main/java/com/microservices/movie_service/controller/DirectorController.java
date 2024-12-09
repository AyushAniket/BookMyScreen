package com.microservices.movie_service.controller;

import com.microservices.movie_service.dto.DirectorRequest;
import com.microservices.movie_service.model.Director;
import com.microservices.movie_service.service.DirectorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/directors/")
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;


    @GetMapping("getAll")
    public List<Director> getAll() {
        return directorService.getAll();
    }

    @PostMapping("add")
    @CircuitBreaker(name="director", fallbackMethod = "fallback")
    @Retry(name="director")
    public void add(@RequestBody DirectorRequest directorRequestDto) {
        directorService.add(directorRequestDto);
    }
    private Director fallback(DirectorRequest directorRequest, RuntimeException runtimeException)  throws RuntimeException{
        return null;
    }


}
