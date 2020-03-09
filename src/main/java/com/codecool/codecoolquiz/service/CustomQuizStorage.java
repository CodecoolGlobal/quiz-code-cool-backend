package com.codecool.codecoolquiz.service;

import com.codecool.codecoolquiz.model.CustomQuiz;
import com.codecool.codecoolquiz.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomQuizStorage {

    private List<CustomQuiz> customQuizzes = new ArrayList<>();

    public List<CustomQuiz> getAll() {
        return customQuizzes;
    }

    public void add(CustomQuiz customQuiz) {
        customQuizzes.add(customQuiz);
    }

    @Override
    public String toString() {
        return "CustomQuizStorage{" +
                "customQuizzes=" + customQuizzes +
                '}';
    }

    public List<Question> getQuestionsForCustomQuizById(int id) {
        return Objects.requireNonNull(getAll()
                .stream()
                .filter(customQuiz -> customQuiz.getId() == id)
                .findFirst().orElse(null))
                .getQuestions();
    }
}
