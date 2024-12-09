package com.microservices.movie_service.repository;

import com.microservices.movie_service.model.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieImageRepository extends JpaRepository<MovieImage, Integer> {
}
