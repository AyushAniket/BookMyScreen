package com.microservices.movie_service.repository;

import com.microservices.movie_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getCategoryByCategoryId(int categoryId);
}
