package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryStorage {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void add(Category category) {
        categoryRepository.save(category);
    }

    public Category getById(int id) {
        return categoryRepository.getOne(id);
    }
}

