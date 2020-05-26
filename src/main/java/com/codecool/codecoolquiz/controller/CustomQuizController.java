package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizRequestBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.CustomQuizResponseBody;
import com.codecool.codecoolquiz.model.RequestResponseBody.QuestionBody;
import com.codecool.codecoolquiz.security.JwtTokenFilter;
import com.codecool.codecoolquiz.security.JwtTokenServices;
import com.codecool.codecoolquiz.service.CustomQuizStorage;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/customquizzes")
public class CustomQuizController {

    @Autowired
    JwtTokenServices jwtTokenServices;

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
    public void saveNewQuiz(@RequestBody CustomQuizRequestBody quizBody, HttpServletRequest req) {
        String username = getUsernameFromToken(req);

        customQuizStorage.saveQuizByUserName(username, quizBody);
    }

    private String getUsernameFromToken(HttpServletRequest req) {
        String username = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(JwtTokenFilter.TOKEN)) {
                    String cookieValue = cookie.getValue();
                    Claims claims = jwtTokenServices.getClaims(cookieValue);
                    username = claims.getSubject();
                }
            }
        }
    return username;
    }
}
