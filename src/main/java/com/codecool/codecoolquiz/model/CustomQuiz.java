package com.codecool.codecoolquiz.model;

import java.util.HashSet;
import java.util.Set;

public class CustomQuiz {

    private int id;
    private String name;
    private Set<Question> questions = new HashSet<>();

    public CustomQuiz(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void add(Question question) {
        questions.add(question);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Question> getQuestions() {
        return questions;
    }
}
