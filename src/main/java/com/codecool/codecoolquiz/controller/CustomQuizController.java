package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizRequestBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizResponseBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.QuestionBody;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customquizzes")
public class CustomQuizController {

    @Autowired
    CustomQuizStorage customQuizStorage;

    @GetMapping("/{id}")
    public List<QuestionBody> getQuestionBodiesForCustomQuiz(@PathVariable int id) {
        return customQuizStorage.getQuestionBodiesForCustomQuizById(id);
    }

    @GetMapping("")
    public List<CustomQuizResponseBody> getCustomQuizzes() {
        return customQuizStorage.getCustomQuizResponseBodies();
    }

    @PostMapping("")
    public void saveNewQuiz(@RequestBody CustomQuizRequestBody quizBody) {
        customQuizStorage.addQuizBody(quizBody);
    }
}
