package com.maxkucher.note_taking.controllers;

import com.maxkucher.note_taking.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing notes related endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NotesController {
    private final NotesService notesService;
}
