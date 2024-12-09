package com.microservices.movie_service.service;

import com.microservices.movie_service.model.Category;
import com.microservices.movie_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
    }

    public Category getCategoryById(int categoryId) {
        return categoryRepository.getCategoryByCategoryId(categoryId);
    }
}
