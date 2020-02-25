package com.codecool.codecoolquiz.model;

import java.util.List;

public class Question {
    private static int counter = 0;
    private int id;
    private Category category;
    private String type;
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;

    public Category getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public int getId() {
        return id;
    }

    public Question(Category category, String type, String question, String correctAnswer, List<String> incorrectAnswers) {
        id = ++counter;
        this.category = category;
        this.type = type;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", category=" + category +
                ", type='" + type + '\'' +
                ", question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", incorrectAnswers=" + incorrectAnswers +
                '}';
    }
}
