package com.microservices.movie_service.repository;

import com.microservices.movie_service.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    List<Actor> getActorsByMovieMovieId(int movieId);
}
