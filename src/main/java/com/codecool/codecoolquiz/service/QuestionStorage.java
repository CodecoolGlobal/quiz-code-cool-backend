package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.Util;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuestionStorage extends SpecificationArgumentResolver {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryStorage categoryStorage;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void add(Question question) {
        question.setCreationDate(LocalDate.now());
        questionRepository.save(question);
    }

    public Optional<Question> getQuestionById(String questionId) {
        return questionRepository.findById(Integer.parseInt(questionId));
    }

    public Question find(int id) {
        return questionRepository.findById(id).orElse(null);
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

    public void remove(String questionId) {
        Question question = find(Integer.parseInt(questionId));
        log.info("Delete question: " + question.toString());
        questionRepository.delete(question);
    }
}
