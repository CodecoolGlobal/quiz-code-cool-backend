package com.codecool.codecoolquiz.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Question {
    private String category;
    private String type;
    private String question;
    private String correct_answer;
    private List<String> incorrect_answers;
}
