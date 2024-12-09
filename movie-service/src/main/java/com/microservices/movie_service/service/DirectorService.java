package com.microservices.movie_service.service;

import com.microservices.movie_service.dto.DirectorRequest;
import com.microservices.movie_service.model.City;
import com.microservices.movie_service.model.Director;
import com.microservices.movie_service.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DirectorService {
    private final DirectorRepository directorRepository;
    private final WebClient.Builder webClientBuilder;

    @Cacheable(value = "directors")
    public List<Director> getAll() {
        return directorRepository.findAll(Sort.by(Sort.Direction.ASC, "directorName"));
    }

    public Director getDirectorById(int directorId) {
        return directorRepository.getDirectorByDirectorId(directorId);
    }

    @CacheEvict(value = "directors", allEntries = true)
    public void add(DirectorRequest directorRequest) {
        Boolean result = webClientBuilder.build().get()
                .uri("http://USERSERVICE/api/user/isUserAdmin")
                .header("Authorization", "Bearer " + directorRequest.token())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (Boolean.TRUE.equals(result)) {
            Director director = Director.builder()
                    .directorName(directorRequest.directorName())
                    .build();
            directorRepository.save(director);
        }
        throw new RuntimeException("Authorization error");
    }
}
