package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.Util;
import com.codecool.codecoolquiz.model.AppUser;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.RequestResponseBody.QuestionBody;
import com.codecool.codecoolquiz.model.exception.NotFoundException;
import com.codecool.codecoolquiz.model.exception.UnsuccessfulDeletion;
import com.codecool.codecoolquiz.repository.CustomQuizRepository;
import com.codecool.codecoolquiz.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionStorage extends SpecificationArgumentResolver {

    @Autowired
    Util util;

    @Autowired
    AppUserStorage appUserStorage;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryStorage categoryStorage;

    @Autowired
    CustomQuizRepository customQuizRepository;

    public void saveQuizByUserName(String username, Question question) {
        AppUser appUser = appUserStorage.getByName(username);
        question.setCreationDate(LocalDate.now());
        question.setAppUser(appUser);
        questionRepository.save(question);
    }

    public void addAll(List<Question> questions) {
        questionRepository.saveAll(questions);
    }

    public Question find(int id) {
        return questionRepository.findById(id).orElseThrow(() -> new NotFoundException("Question not found."));
    }

    public QuestionBody getQuestionBodyFromId(int id) {
        Question question = find(id);
        return new QuestionBody(question);
    }

    public List<QuestionBody> findAllBySpec(Specification<Question> customerSpec, Integer numOfNeededItems) {
        List<Question> filteredQuestions = questionRepository.findAll(customerSpec);
        if (numOfNeededItems != null) {
            filteredQuestions = util.getRandomQuestionsFromList(filteredQuestions, numOfNeededItems);
        }
        return filteredQuestions.stream().map(q -> new QuestionBody(q)).collect(Collectors.toList());
    }

    public void validateQuestionById(String questionId) throws Exception {
        Question questionToValidate = questionRepository.findById(Integer.parseInt(questionId)).orElseThrow(Exception::new);
        questionToValidate.setValidated(true);
        questionToValidate.setValidationDate(LocalDate.now());
        questionRepository.save(questionToValidate);
    }

    public void remove(int questionId) {
        try {
            questionRepository.deleteById(questionId);
        } catch (DataIntegrityViolationException e) {
            throw new UnsuccessfulDeletion("Question " + questionId + " cannot be deleted, as long as it appears in a quiz.");
        }
    }

}
