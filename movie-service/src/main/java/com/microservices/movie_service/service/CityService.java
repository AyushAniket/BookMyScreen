package com.microservices.movie_service.service;

import com.microservices.movie_service.model.City;
import com.microservices.movie_service.model.Movie;
import com.microservices.movie_service.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.microservices.movie_service.dto.CityRequest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;

    public List<City> getCitiesByMovieId(int movieId) {
        return cityRepository.getCitiesByMovieMovieId(movieId);
    }

    public List<City> getAll() {
        return cityRepository.findAll(Sort.by(Sort.Direction.ASC, "cityName"));
    }

    public void add(CityRequest cityRequest) {
        Boolean result = webClientBuilder.build().get()
                .uri("http://USERSERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + cityRequest.token())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            Movie movie = movieService.getMovieById(cityRequest.movieId());
            for (String cityName: cityRequest.cityNameList()) {
                City city = City.builder()
                        .cityName(cityName)
                        .movie(movie)
                        .build();
                cityRepository.save(city);
            }
        }
        throw new RuntimeException("Authorization error");
    }
}
