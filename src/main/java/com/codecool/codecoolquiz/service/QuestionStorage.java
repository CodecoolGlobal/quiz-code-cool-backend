package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.Util;
import com.codecool.codecoolquiz.model.FilterCriteria;
import com.codecool.codecoolquiz.model.Question;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionStorage {

    @Autowired
    Util util;

    private List<Question> questions = new ArrayList<>();

    public List<Question> getAll() {
        return questions;
    }

    public void add(Question question) {
        questions.add(question);
    }

    @Override
    public String toString() {
        return "QuestionStorage{" +
                "questions=" + questions +
                '}';
    }

    public Question getQuestionById(String questionId) throws Exception {
        return questions.stream()
                .filter(question -> question.getId() == Integer.parseInt(questionId))
                .findFirst()
                .orElseThrow(() -> new Exception("Question not found"));
    }

    public List<Question> getFilteredQuestions(String category, String type, String amount) {

        //List<Pair<String, String>> filters = filterCriteria.getFilters();

        List<Question> resultList = getAll()
                .stream()
                .filter(question -> category == null || question.getCategory().getId() == Integer.parseInt(category))
                .filter(question -> type == null || question.getType().equals(type))
                .collect(Collectors.toList());

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
