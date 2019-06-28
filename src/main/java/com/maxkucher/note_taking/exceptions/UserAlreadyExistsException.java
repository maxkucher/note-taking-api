package com.maxkucher.note_taking.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends NoteTakerException {
    public UserAlreadyExistsException() {
        super("Unable to create user. User already exists", HttpStatus.BAD_REQUEST);
    }
}
