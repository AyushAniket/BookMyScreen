package com.microservices.movie_service.service;

import com.microservices.movie_service.dto.ActorRequest;
import com.microservices.movie_service.model.Actor;
import com.microservices.movie_service.model.Movie;
import com.microservices.movie_service.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActorService {
    private final ActorRepository actorRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;

    public List<Actor> getActorsByMovieId(int movieId) {
        return actorRepository.getActorsByMovieMovieId(movieId);
    }
    @Cacheable(value = "actors")
    public List<Actor> getAll() {
        return actorRepository.findAll(Sort.by(Sort.Direction.ASC, "actorName"));
    }

    @CacheEvict(value = "actors", allEntries = true)
    public void addActors(@RequestBody ActorRequest actorRequest){
        Boolean result = webClientBuilder.build().get()
                .uri("http://USERSERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + actorRequest.token())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            Movie movie = movieService.getMovieById(actorRequest.movieId());

            for (String actorName: actorRequest.actorNameList()) {
                Actor actor = Actor.builder()
                        .actorName(actorName)
                        .movie(movie)
                        .build();
                actorRepository.save(actor);
            }
       }
        throw new RuntimeException("Authorization error");
    }
}