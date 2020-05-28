package com.codecool.codecoolquiz.model.exception;

import org.springframework.security.core.AuthenticationException;

public class EmailAlreadyExistsException extends AuthenticationException {

    public EmailAlreadyExistsException() {
        super("Email is already exists.");
    }
}