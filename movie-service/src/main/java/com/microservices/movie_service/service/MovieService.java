package com.microservices.movie_service.service;

import com.microservices.movie_service.dto.MovieRequest;
import com.microservices.movie_service.dto.MovieResponse;
import com.microservices.movie_service.model.Category;
import com.microservices.movie_service.model.Director;
import com.microservices.movie_service.model.Movie;
import com.microservices.movie_service.repository.MovieRepository;
import com.microservices.movie_service.repository.MovieTheaterTimeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class MovieService {
    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final DirectorService directorService;
    private final MovieTheaterTimeRepository movieTheaterTimeRepository;
    private final WebClient.Builder webClientBuilder;

    @Cacheable(value = "displaying_movies")
    public List<MovieResponse> getAllDisplayingMovies() {
        return movieRepository.getAllDisplayingMovies();
    }

    @Cacheable(value = "comingSoon_movies")
    public List<MovieResponse> getAllComingSoonMovies() {
        return movieRepository.getAllComingSoonMovies();
    }

    public MovieResponse getMovieByMovieId(int movieId) {
        return movieRepository.getMovieById(movieId);
    }

    public Movie getMovieById(int movieId) {
        return movieRepository.getMovieByMovieId(movieId);
    }


    public Movie addMovie(MovieRequest movieRequest){
        Boolean result = webClientBuilder.build().get()
                .uri("http://USERSERVICE/api/user/users/isUserAdmin")
                .header("Authorization", "Bearer " + movieRequest.token())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            Category category = categoryService.getCategoryById(movieRequest.categoryId());
            Director director = directorService.getDirectorById(movieRequest.directorId());

            Movie movie = Movie.builder()
                    .movieName(movieRequest.movieName())
                    .description(movieRequest.description())
                    .duration(movieRequest.duration())
                    .releaseDate(movieRequest.releaseDate())
                    .isDisplay(movieRequest.isDisplay())
                    .movieTrailerUrl(movieRequest.movieTrailerUrl())
                    .category(category)
                    .director(director)
                    .build();
            movieRepository.save(movie);
            log.info("Movie added successfully");
            return movie;
        }
        throw new RuntimeException("Authorization error");
    }

}
