package com.microservices.movie_service.dto;

import java.util.Date;
import java.util.List;

public record MovieRequest(String movieName, String description, int duration, Date releaseDate,
                           boolean isDisplay, String movieTrailerUrl, int categoryId,
                           int directorId, List<Integer> actors, List<Integer> cities, String token) {

}
