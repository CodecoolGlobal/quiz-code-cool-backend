package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.RequestResponseBody.QuestionBody;
import com.codecool.codecoolquiz.security.JwtTokenServices;
import com.codecool.codecoolquiz.service.QuestionStorage;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    JwtTokenServices jwtTokenServices;

    @Autowired
    QuestionStorage questionStorage;

    @GetMapping("")
    public List<QuestionBody> findQuestions(
            @And({
                    @Spec(path = "category.id", params = "category", spec = Equal.class),
                    @Spec(path = "type", spec = Equal.class),
                    @Spec(path = "isValidated", params = "validated", spec = Equal.class)
            }) Specification<Question> customerSpec,
            Integer amount) {
        return questionStorage.findAllBySpec(customerSpec, amount);
    }

    @GetMapping("/{questionId}")
    public QuestionBody getQuestion(@PathVariable int questionId) {
        return questionStorage.getQuestionBodyFromId(questionId);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable int questionId) {
        questionStorage.remove(questionId);
    }

    @PutMapping("/{questionId}")
    public void validateQuestion(@PathVariable String questionId) throws Exception {
        questionStorage.validateQuestionById(questionId);
    }

    @PostMapping("")
    public void saveNewQuestion(@RequestBody Question question, HttpServletRequest req) {
        String username = jwtTokenServices.getUsernameFromToken(req);
        questionStorage.saveQuizByUserName(username, question);
    }
}
