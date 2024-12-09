package com.microservices.movie_service.service;

import com.microservices.movie_service.dto.ImageRequest;
import com.microservices.movie_service.model.Movie;
import com.microservices.movie_service.model.MovieImage;
import com.microservices.movie_service.repository.MovieImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class MovieImageService {
    private final MovieImageRepository movieImageRepository;
    private final MovieService movieService;
    private final WebClient.Builder webClientBuilder;


    public void addMovieImage(ImageRequest imageRequest) {
        Boolean result = webClientBuilder.build().get()
                .uri("http://USERSERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + imageRequest.token())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            Movie movie = movieService.getMovieById(imageRequest.movieId());

            MovieImage image = new MovieImage();
            image.setImageUrl(imageRequest.imageUrl());
            image.setMovie(movie);

            movieImageRepository.save(image);
        }
        throw new RuntimeException("Authorization error");
    }
}
