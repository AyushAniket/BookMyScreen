package com.microservices.movie_service.controller;

import com.microservices.movie_service.dto.ActorRequest;
import com.microservices.movie_service.model.Actor;
import com.microservices.movie_service.service.ActorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @GetMapping("getActorsByMovieId/{movieId}")
    public List<Actor> getActorsByMovieId(@PathVariable int movieId) {
        return actorService.getActorsByMovieId(movieId);
    }

    @GetMapping("getAll")
    public List<Actor> getAll() {
        return actorService.getAll();
    }


    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name="actor", fallbackMethod="fallback")
    @Retry(name="actor")
    public void addActors(@RequestBody ActorRequest actorRequest) {
        actorService.addActors(actorRequest);
    }
    private void fallback(ActorRequest actorRequest, RuntimeException runtimeException) { }

}
