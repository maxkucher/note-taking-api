package com.maxkucher.note_taking.exceptions;

import java.util.UUID;

public class NoteNotFoundException extends NotetakerException {

    public NoteNotFoundException(UUID id) {
        super(String.format("Note with id %s was not found", id));
    }
}
