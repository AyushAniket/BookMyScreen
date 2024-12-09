package com.microservices.movie_service.controller;

import com.microservices.movie_service.dto.DeleteReviewRequest;
import com.microservices.movie_service.dto.ReviewRequest;
import com.microservices.movie_service.model.Review;
import com.microservices.movie_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/reviews/")
@CrossOrigin
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("getReviewsByMovieId/{movieId}/{pageNo}/{pageSize}")
    public List<Review> getReviewsByMovieId(@PathVariable int movieId, @PathVariable int pageNo,
                                            @PathVariable int pageSize) {
        return reviewService.getReviewsByMovieId(movieId, pageNo, pageSize);
    }

    @GetMapping("getCountOfReviews/{movieId}")
    public int getNumberOfReviewsByMovieId(@PathVariable int movieId) {
        return reviewService.getNumberOfReviewsByMovieId(movieId);
    }

    @PostMapping("add")
    @CircuitBreaker(name = "review", fallbackMethod = "fallback")
    @Retry(name="review")
    public void addReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.addReview(reviewRequest);
    }

    private Review fallback(ReviewRequest reviewRequest, RuntimeException runtimeException) throws RuntimeException{
        return null;
    }

    @PostMapping("delete")
    public void deleteReview(@RequestBody DeleteReviewRequest deleteReviewRequest) {
        reviewService.deleteReview(deleteReviewRequest);
    }

}
