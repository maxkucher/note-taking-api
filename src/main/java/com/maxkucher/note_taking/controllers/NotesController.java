package com.maxkucher.note_taking.controllers;

import com.maxkucher.note_taking.dto.NoteDto;
import com.maxkucher.note_taking.dto.NoteTakerResponse;
import com.maxkucher.note_taking.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Controller for managing notes related endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class NotesController {
    private final NotesService notesService;


    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(notesService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(notesService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(notesService.create(noteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,
                                    @Valid @RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(notesService
                .update(noteDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        notesService.delete(id);
        return ResponseEntity.ok(new NoteTakerResponse(true,
                String.format("Note with id %s was deleted successfully",
                        id.toString())));
    }
}
