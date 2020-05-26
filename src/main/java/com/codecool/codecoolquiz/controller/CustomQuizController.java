package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizRequestBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizResponseBody;
import com.codecool.codecoolquiz.model.exception.NotFoundException;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customquizzes")
public class CustomQuizController {

    @Autowired
    CustomQuizStorage customQuizStorage;

    @GetMapping("/{id}")
    public ResponseEntity getQuestionBodiesForCustomQuiz(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(customQuizStorage.getQuestionBodiesForCustomQuizById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
