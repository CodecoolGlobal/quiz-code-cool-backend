package com.codecool.codecoolquiz.config;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.service.CategoryStorage;
import com.codecool.codecoolquiz.service.QuestionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Initializer {

    @Autowired
    CategoryStorage categoryStorage;

    @Autowired
    QuestionStorage questionStorage;

    public void loadInitData() {
        loadInitCategories();
    }

    private void loadInitCategories() {
        categoryStorage.add(new Category(1, "Python"));
        categoryStorage.add(new Category(2, "Java"));
        categoryStorage.add(new Category(3, "CSS"));
        categoryStorage.add(new Category(4, "SQL"));
        categoryStorage.add(new Category(5, "HTML"));
        categoryStorage.add(new Category(6, "React"));
    }

    private void loadInitQuestions() {
    }

}
