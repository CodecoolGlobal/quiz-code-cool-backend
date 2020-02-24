package com.codecool.codecoolquiz.config;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.CategoryStorage;
import com.codecool.codecoolquiz.service.QuestionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Initializer {

    @Autowired
    CategoryStorage categoryStorage;

    @Autowired
    QuestionStorage questionStorage;

    public void loadInitData() throws Exception {
        loadInitCategories();
        loadInitQuestions();
    }

    private void loadInitCategories() throws Exception {
        categoryStorage.add(new Category(1, "Python"));
        categoryStorage.add(new Category(2, "Java"));
        categoryStorage.add(new Category(3, "CSS"));
        categoryStorage.add(new Category(4, "SQL"));
        categoryStorage.add(new Category(5, "HTML"));
        categoryStorage.add(new Category(6, "React"));
    }

    private void loadInitQuestions() throws Exception {
        questionStorage.add(new Question(categoryStorage.getById(1),
                "multiple",
                "Which company was established on April 1st, 1976 by Steve Jobs, Steve Wozniak and Ronald Wayne?",
                "Apple",
                Arrays.asList("Microsoft", "Atari", "Commodore")));
        questionStorage.add(new Question(
                categoryStorage.getById(1),
                "multiple",
                "In any programming language, what is the most common way to iterate through an array?",
                "&#039;For&#039; loops",
                Arrays.asList("&#039;If&#039; Statements", "&#039;Do-while&#039; loops", "&#039;While&#039; loops")
        ));
    }
}
