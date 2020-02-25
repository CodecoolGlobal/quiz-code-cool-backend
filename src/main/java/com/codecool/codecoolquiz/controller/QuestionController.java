package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.QuestionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuestionController {

    @Autowired
    QuestionStorage questionStorage;

    @GetMapping("/questions")
    public List<Question> getRequestedQuestions(@RequestParam String category, @RequestParam String type, @RequestParam String amount) {
        List<Question> resultList = questionStorage.getAll().stream()
                .filter(question -> question.getCategory().getName().equals(category) && question.getType().equals(type))
                .collect(Collectors.toList());
        return resultList.size() >= Integer.parseInt(amount) ? resultList : null;
    }
}
