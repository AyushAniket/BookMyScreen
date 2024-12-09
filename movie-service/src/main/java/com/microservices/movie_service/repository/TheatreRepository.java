package com.microservices.movie_service.repository;

import com.microservices.movie_service.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository  extends JpaRepository<Theater, Integer> {
}
