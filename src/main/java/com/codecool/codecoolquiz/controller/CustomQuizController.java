package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
public class CustomQuizController {

    @Autowired
    CustomQuizStorage customQuizStorage;

    @GetMapping("/customquizzes/{id}")
    public List<Question> getQuestionsForCustomQuiz(@PathVariable int id) {
        return customQuizStorage.getQuestionsForCustomQuizById(id);
    }

    @GetMapping("/customquizzes")
    public List<CustomQuiz> getCustomQuizzes() {
        return customQuizStorage.getAll();
    }
}
