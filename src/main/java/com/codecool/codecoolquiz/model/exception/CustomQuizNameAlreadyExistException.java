package com.codecool.codecoolquiz.model.exception;

public class CustomQuizNameAlreadyExistException extends RuntimeException {
    public CustomQuizNameAlreadyExistException(String name) {
        super("Custom quiz cannot be saved. It already exists with the name '" + name + "'.");
    }
}
