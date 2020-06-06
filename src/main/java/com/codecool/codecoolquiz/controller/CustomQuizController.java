package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizRequestBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizResponseBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.QuestionBody;
import com.codecool.codecoolquiz.security.JwtTokenServices;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/customquizzes")
public class CustomQuizController {

    @Autowired
    JwtTokenServices jwtTokenServices;

    @Autowired
    CustomQuizStorage customQuizStorage;

    @GetMapping("/{id}/questions")
    public List<QuestionBody> getQuestionBodiesForCustomQuiz(@PathVariable int id) {
        return customQuizStorage.getQuestionBodiesForCustomQuizById(id);
    }

    @GetMapping("")
    public List<CustomQuizResponseBody> getCustomQuizzes() {
        return customQuizStorage.getCustomQuizResponseBodies();
    }

    @GetMapping("/{id}")
    public List<CustomQuizResponseBody> getCustomQuizzesByUser(@PathVariable int id) {
        return customQuizStorage.getCustomQuizResponseBodiesByUserId(id);
    }

    @PostMapping("")
    public void saveNewQuiz(@RequestBody CustomQuizRequestBody quizBody, HttpServletRequest req) {
        String username = jwtTokenServices.getUsernameFromToken(req);
        customQuizStorage.saveQuizByUserName(username, quizBody);
    }

}
