package com.maxkucher.note_taking.exceptions.handlers;


import com.maxkucher.note_taking.dto.NoteTakerResponse;
import com.maxkucher.note_taking.exceptions.NoteNotFoundException;
import com.maxkucher.note_taking.exceptions.NoteTakerException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {NoteTakerException.class})
    public ResponseEntity<NoteTakerResponse> handleNoteNotFoundException(NoteTakerException e) {
        return new ResponseEntity<>(new NoteTakerResponse(e.getMessage()),
                e.getStatus());
    }

}
