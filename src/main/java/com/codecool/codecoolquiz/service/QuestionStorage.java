package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.Util;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.repository.QuestionRepository;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionStorage extends SpecificationArgumentResolver {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryStorage categoryStorage;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void add(Question question) {
        questionRepository.save(question);
    }

    public Optional<Question> getQuestionById(String questionId) {
        return questionRepository.findById(Integer.parseInt(questionId));
    }

    public List<Question> findAll(Specification<Question> customerSpec) {
        return questionRepository.findAll(customerSpec);
    }

    public void validateQuestionById(String questionId) throws Exception {
        Question questionToValidate = questionRepository.findById(Integer.parseInt(questionId)).orElseThrow(Exception::new);
        questionToValidate.setValidated(true);
        questionToValidate.setValidationDate(LocalDate.now());
        questionRepository.save(questionToValidate);
    }
}
