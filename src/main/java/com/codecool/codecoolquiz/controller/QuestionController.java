package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.Util;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.QuestionStorage;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

    @Autowired
    QuestionStorage questionStorage;

    @Autowired
    Util util;

    @GetMapping("/questions")
    public List<Question> findQuestions(
            @And({
                    @Spec(path = "category.id", params = "category", spec = Equal.class),
                    @Spec(path = "type", spec = Equal.class),
                    @Spec(path = "isValidated", params = "validated", spec = Equal.class)
            }) Specification<Question> customerSpec,
            String amount) {

        List<Question> filteredQuestions = questionStorage.findAll(customerSpec);
        return util.getRandomQuestionsFromList(filteredQuestions, amount);
    }

    @GetMapping("/questions/{questionId}")
    public Optional<Question> getQuestion(@PathVariable String questionId) {
        return questionStorage.getQuestionById(questionId);
    }

    @PutMapping("/questions/{questionId}")
    public void validateQuestion(@PathVariable String questionId) throws Exception {
        questionStorage.validateQuestionById(questionId);
    }

    @PostMapping("questions/add")
    public void saveNewQuestion(@RequestBody Question question) {
        questionStorage.add(question);
    }
}
