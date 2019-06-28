package com.maxkucher.note_taking.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class NoteNotFoundException extends NoteTakerException {

    public NoteNotFoundException(UUID id) {
        super(String.format("Note with id %s was not found", id), HttpStatus.NOT_FOUND);
    }
}
