package com.microservices.movie_service.dto;

import java.util.List;

public record ActorRequest(int movieId, List<String> actorNameList, String token) {
}
