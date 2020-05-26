package com.codecool.codecoolquiz.model.RequestResponseBody;

import com.codecool.codecoolquiz.model.AppUser;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponseBody {
    private Integer id;
    private String username;
    private LocalDate registrationDate;
    private List<ReferenceToOtherEntity> questions;
    private List<ReferenceToOtherEntity> customQuizzes;

    public UserResponseBody(AppUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.registrationDate = user.getRegistrationDate();
        this.questions = user.getQuestions()
                .stream()
                .map(q -> new ReferenceToOtherEntity(q.getId(), q.getQuestion()))
                .collect(Collectors.toList());
        this.customQuizzes = user.getCustomQuizzes()
                .stream()
                .map(q -> new ReferenceToOtherEntity(q.getId(), q.getName()))
                .collect(Collectors.toList());
    }
}
