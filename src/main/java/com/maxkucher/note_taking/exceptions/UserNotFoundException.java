package com.maxkucher.note_taking.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UserNotFoundException extends NoteTakerException {
    public UserNotFoundException(UUID id) {
        super(String.format("User with id %s was not found", id.toString()),
                HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(String email) {
        super(String.format("User with email %s was not found", email),
                HttpStatus.NOT_FOUND);
    }
}
