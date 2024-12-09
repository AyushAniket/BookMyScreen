package com.microservices.movie_service.controller;
import com.microservices.movie_service.model.Category;
import com.microservices.movie_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/categories/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping("getAll")
    public List<Category> getAll() {
        return categoryService.getAll();
    }
}
