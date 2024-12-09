package com.microservices.movie_service.dto;

public record ReviewRequest(String userId, String reviewText, String reviewBy, String reviewByUserId,
                            Integer movieId, String token) {
}
