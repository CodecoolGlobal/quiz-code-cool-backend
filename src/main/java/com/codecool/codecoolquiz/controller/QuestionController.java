package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.FilterCriteria;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.QuestionStorage;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionStorage questionStorage;

    @Autowired
    FilterCriteria filterCriteria;

    @GetMapping("/questions")
    public List<Question> getRequestedQuestions(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String amount) {

        filterCriteria.setFilters(
                new Pair<>("category", category),
                new Pair<>("type", type),
                new Pair<>("amount", amount));

        return questionStorage.getFilteredQuestions(filterCriteria);
    }

    @GetMapping("/questions/{questionId}")
    public Question getQuestion(@PathVariable String questionId) throws Exception {
        return questionStorage.getQuestionById(questionId);
    }

    @PostMapping("questions/add")
    public void saveNewQuestion(@RequestBody Question question) {
        questionStorage.add(question);
    }
}
