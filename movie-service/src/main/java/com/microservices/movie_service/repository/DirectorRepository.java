package com.microservices.movie_service.repository;

import com.microservices.movie_service.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {
    Director getDirectorByDirectorId(int directorId);
}
