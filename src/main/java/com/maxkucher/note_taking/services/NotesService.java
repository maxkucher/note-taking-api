package com.maxkucher.note_taking.services;


import com.maxkucher.note_taking.dto.NoteDto;
import com.maxkucher.note_taking.entities.Note;
import com.maxkucher.note_taking.exceptions.NoteNotFoundException;
import com.maxkucher.note_taking.repositories.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotesService {
    private NotesRepository notesRepository;


    public Note getById(UUID id) {
        return notesRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }

    public List<Note> getAll() {
        return notesRepository
                .findAll();
    }

    public Note create(NoteDto noteDto) {
        Note note = Note.builder()
                .noteTitle(noteDto.getNoteTitle())
                .noteContent(noteDto.getNoteContent())
                .build();
        return notesRepository.save(note);
    }

    public Note update(NoteDto noteDto, UUID id) {
        Note note = getById(id);
        note.setNoteTitle(noteDto.getNoteTitle());
        note.setNoteContent(noteDto.getNoteContent());
        return notesRepository.save(note);
    }
}
