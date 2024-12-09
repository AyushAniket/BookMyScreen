package com.microservices.movie_service.dto;

public record ImageRequest(int movieId, String imageUrl, String token) {
}
