package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.service.CategoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryStorage categoryStorage;

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryStorage.getAll();
    }
}
