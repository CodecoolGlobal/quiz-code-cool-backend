package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryStorage {

    private List<Category> categories = new ArrayList<>();

    public List<Category> getAll() {
        return categories;
    }

    public void add(Category category) {
        categories.add(category);
    }

    public Category getById(int id) throws Exception {
        return categories.stream().filter(category -> category.getId() == id).findFirst()
                .orElseThrow(() -> new Exception("No Category found"));
    }
}

