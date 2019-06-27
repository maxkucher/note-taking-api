package com.maxkucher.note_taking.controllers;

import com.maxkucher.note_taking.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing notes related endpoints
 */
@RestController
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;
}
