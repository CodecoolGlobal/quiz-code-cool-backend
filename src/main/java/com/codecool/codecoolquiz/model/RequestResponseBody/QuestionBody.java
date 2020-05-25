package com.codecool.codecoolquiz.model.RequestResponseBody;

import com.codecool.codecoolquiz.model.Category;
import com.codecool.codecoolquiz.model.Question;
import com.codecool.codecoolquiz.model.Type;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class QuestionBody {

    private Integer id;
    private Category category;
    private Type type;
    private String question;
    private String correctAnswer;
    private List<String> incorrectAnswers;
    private LocalDate creationDate;
    private LocalDate validationDate;
    private boolean isValidated;
    private UserDataForQuestionBody appUser;

    public QuestionBody(Question question) {
        this.id = question.getId();
        this.category = question.getCategory();
        this.type = question.getType();
        this.question = question.getQuestion();
        this.correctAnswer = question.getCorrectAnswer();
        this.incorrectAnswers = question.getIncorrectAnswers();
        this.creationDate = question.getCreationDate();
        this.validationDate = question.getValidationDate();
        this.isValidated = question.isValidated();
        this.appUser = new UserDataForQuestionBody(question.getAppUser().getId(), question.getAppUser().getUsername());
    }
}
