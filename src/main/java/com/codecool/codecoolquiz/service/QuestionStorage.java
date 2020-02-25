package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionStorage {

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
}
