package com.maxkucher.note_taking.controllers;

import com.maxkucher.note_taking.dto.NoteDto;
import com.maxkucher.note_taking.dto.NoteTakerResponse;
import com.maxkucher.note_taking.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
    public ResponseEntity<?> getAll(Principal principal) {
        return ResponseEntity.ok(notesService.getAllByUserEmail(principal.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id, Principal principal) {
        return ResponseEntity.ok(notesService.getById(id, principal.getName()));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody NoteDto noteDto, Principal principal) {
        return ResponseEntity.ok(notesService.create(noteDto, principal.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,
                                    @Valid @RequestBody NoteDto noteDto,
                                    Principal principal) {
        return ResponseEntity.ok(notesService
                .update(noteDto, id, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id, Principal principal) {
        notesService.delete(id, principal.getName());
        return ResponseEntity.ok(new NoteTakerResponse(true,
                String.format("Note with id %s was deleted successfully",
                        id.toString())));
    }
}
