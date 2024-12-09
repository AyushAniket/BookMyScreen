package com.microservices.movie_service.dto;

import java.util.List;

public record CityRequest(int movieId, List<String> cityNameList, String token) {
}
