package com.codecool.codecoolquiz.repository;

import com.codecool.codecoolquiz.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
