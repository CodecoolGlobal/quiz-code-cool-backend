package com.codecool.codecoolquiz.controller;

import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.service.QuestionStorage;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionStorage questionStorage;

    @GetMapping("")
    public List<Question> findQuestions(
            @And({
                    @Spec(path = "category.id", params = "category", spec = Equal.class),
                    @Spec(path = "type", spec = Equal.class),
                    @Spec(path = "isValidated", params = "validated", spec = Equal.class)
            }) Specification<Question> customerSpec,
            Integer amount) {
        return questionStorage.findAllBySpec(customerSpec, amount);
    }

    @GetMapping("/{questionId}")
    public Question getQuestion(@PathVariable int questionId) {
        return questionStorage.find(questionId);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestion(@PathVariable String questionId) {
        questionStorage.remove(questionId);
    }

    @PutMapping("/{questionId}")
    public void validateQuestion(@PathVariable String questionId) throws Exception {
        questionStorage.validateQuestionById(questionId);

    }

    @PostMapping("")
    public void saveNewQuestion(@RequestBody Question question) {
        questionStorage.add(question);
    }
}
