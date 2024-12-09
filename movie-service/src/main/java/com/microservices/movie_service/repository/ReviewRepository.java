package com.microservices.movie_service.repository;

import com.microservices.movie_service.model.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> getReviewsByMovieMovieId(int movieId, Pageable pageable);

    int countReviewByMovieMovieId(int movieId);
}
