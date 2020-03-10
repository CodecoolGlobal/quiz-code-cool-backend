package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.Util;
import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.FilterCriteria;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.repository.QuestionRepository;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionStorage {

    @Autowired
    Util util;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryStorage categoryStorage;

    private List<Question> questions = new ArrayList<>();

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public void add(Question question) {
        questionRepository.save(question);
    }

    public Question getQuestionById(String questionId) {
        return questionRepository.getOne(Integer.parseInt(questionId));
    }

    public List<Question> getFilteredQuestions(String category, String type, String amount) {

        List<Question> resultList;

        if (category != null) {
            Category questionCategory = categoryStorage.getById(Integer.parseInt(category));
            if (type != null) {
                resultList = questionRepository.findByCategoryAndType(questionCategory, type);
            } else {
                resultList = questionRepository.findByCategory(questionCategory);
            }
        } else if (type != null) {
            resultList = questionRepository.findByType(type);
        } else {
            resultList = questionRepository.findAll();
        }

        if (amount == null) {
            return resultList;
        }

        if (resultList.size() < Integer.parseInt(amount)) {
            return null;
        } else {
            return util.getRandomQuestionsFromList(resultList, Integer.parseInt(amount));
        }

    }
}
