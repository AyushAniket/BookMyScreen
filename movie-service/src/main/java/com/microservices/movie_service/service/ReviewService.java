package com.microservices.movie_service.service;

import com.microservices.movie_service.dto.DeleteReviewRequest;
import com.microservices.movie_service.dto.ReviewRequest;
import com.microservices.movie_service.model.Movie;
import com.microservices.movie_service.model.Review;
import com.microservices.movie_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;

    
    public List<Review> getReviewsByMovieId(int movieId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return reviewRepository.getReviewsByMovieMovieId(movieId, pageable);
    }
    
    public int getNumberOfReviewsByMovieId(int movieId) {
        return reviewRepository.countReviewByMovieMovieId(movieId);
    }
    
    public void deleteReview(DeleteReviewRequest deleteReviewRequest) {

        Boolean result = webClientBuilder.build().get()
                .uri("http://USERSERVICE/api/user/users/isUserCustomer")
                .header("Authorization","Bearer " + deleteReviewRequest.token())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (Boolean.TRUE.equals(result)) {
            reviewRepository.deleteById(deleteReviewRequest.reviewId());
        }
    }

    public void addReview(ReviewRequest reviewRequest) {
        Boolean result = webClientBuilder.build().get()
                .uri("http://USERSERVICE/api/user/users/isUserCustomer")
                .header("Authorization","Bearer " + reviewRequest.token())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {

            Movie movie = movieService.getMovieById(reviewRequest.movieId());

            Review review = Review.builder()
                    .reviewByUserId(reviewRequest.reviewByUserId())
                    .reviewBy(reviewRequest.reviewBy())
                    .reviewText(reviewRequest.reviewText())
                    .movie(movie)
                    .build();

            reviewRepository.save(review);
        }
        throw new RuntimeException("Authorization error");
    }
}
