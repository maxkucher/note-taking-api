package com.maxkucher.note_taking.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NoteTakerException extends RuntimeException {
    @Getter
    private HttpStatus status;

    public NoteTakerException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public NoteTakerException(String message, HttpStatus status) {
        super(message, null, false, false);
        this.status = status;
    }
}

