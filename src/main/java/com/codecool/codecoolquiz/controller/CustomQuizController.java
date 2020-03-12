package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("customquizzes")
    public void saveNewQuiz(@RequestBody CustomQuiz customQuiz) {
        customQuizStorage.add(customQuiz);
    }
}
