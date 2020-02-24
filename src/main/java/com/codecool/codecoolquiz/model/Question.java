package com.codecool.codecoolquiz.model;

import java.util.List;

public class Question {
    private Category category;
    private String type;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;

    public Category getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public List<String> getIncorrect_answers() {
        return incorrect_answers;
    }

    public Question(Category category, String type, String question, String correct_answer, List<String> incorrect_answers) {
        this.category = category;
        this.type = type;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }
}
