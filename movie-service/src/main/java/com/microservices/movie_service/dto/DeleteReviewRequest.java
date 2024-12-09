package com.microservices.movie_service.dto;

public record DeleteReviewRequest(int reviewId, String token) {
}
