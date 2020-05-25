package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.Util;
import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.repository.CustomQuizRepository;
import com.codecool.codecoolquiz.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class QuestionStorage extends SpecificationArgumentResolver {

    @Autowired
    Util util;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryStorage categoryStorage;

    @Autowired
    CustomQuizRepository customQuizRepository;

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void add(Question question) {
        question.setCreationDate(LocalDate.now());
        questionRepository.save(question);
    }

    public void addAll(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    public Question find(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> findAllBySpec(Specification<Question> customerSpec, Integer numOfNeededItems) {
        List<Question> filteredQuestions = questionRepository.findAll(customerSpec);
        if (numOfNeededItems != null) {
            filteredQuestions = util.getRandomQuestionsFromList(filteredQuestions, numOfNeededItems);
        }
        return filteredQuestions;
    }

    public void validateQuestionById(String questionId) throws Exception {
        Question questionToValidate = questionRepository.findById(Integer.parseInt(questionId)).orElseThrow(Exception::new);
        questionToValidate.setValidated(true);
        questionToValidate.setValidationDate(LocalDate.now());
        questionRepository.save(questionToValidate);
    }

    public void remove(String questionId) {
        Question question = find(Integer.parseInt(questionId));
        for (CustomQuiz quiz : question.getQuizzes()) {
            quiz.removeQuestion(question);
        }
        questionRepository.delete(question);

    }

}
