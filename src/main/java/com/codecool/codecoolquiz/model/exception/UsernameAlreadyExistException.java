package com.codecool.codecoolquiz.model.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameAlreadyExistException extends AuthenticationException {

    public UsernameAlreadyExistException() {
        super("Username already exists.");
    }

}