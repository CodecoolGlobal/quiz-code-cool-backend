package com.codecool.codecoolquiz.model.RequestResponseBody;

import com.codecool.codecoolquiz.model.CustomQuiz;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomQuizResponseBody {

    private Integer id;
    private String name;
    private List<QuestionBody> questions;
    private ReferenceToOtherEntity appUser;

    public CustomQuizResponseBody(CustomQuiz customQuiz) {
        this.id = customQuiz.getId();
        this.name = customQuiz.getName();
        this.questions = customQuiz.getQuestions().stream().map(QuestionBody::new).collect(Collectors.toList());
        this.appUser = new ReferenceToOtherEntity(customQuiz.getAppUser().getId(), customQuiz.getAppUser().getUsername());
    }
}
