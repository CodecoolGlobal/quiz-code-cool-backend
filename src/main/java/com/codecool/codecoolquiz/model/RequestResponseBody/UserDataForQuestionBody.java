package com.codecool.codecoolquiz.model.RequestResponseBody;

import lombok.Data;

@Data
public class UserDataForQuestionBody {

    Integer id;
    String username;

    public UserDataForQuestionBody(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}
