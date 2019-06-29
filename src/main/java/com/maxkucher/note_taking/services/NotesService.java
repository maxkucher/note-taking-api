package com.maxkucher.note_taking.services;


import com.maxkucher.note_taking.dto.NoteDto;
import com.maxkucher.note_taking.entities.Note;
import com.maxkucher.note_taking.entities.UserInfo;
import com.maxkucher.note_taking.exceptions.NoteNotFoundException;
import com.maxkucher.note_taking.repositories.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotesService {
    private final NotesRepository notesRepository;
    private final UsersInfoService usersInfoService;


    public Note getById(UUID id, String email) {
        Note note  =  notesRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
        if (note.getUser().getEmail().equals(email)) return note;
        else throw new SecurityException("Unauthorized");
    }

    public List<Note> getAllByUserEmail(String email) {
        return notesRepository
                .findAllByUser_Email(email);
    }

    public Note create(NoteDto noteDto, String email) {
        UserInfo userInfo = usersInfoService.get(email);
        Note note = Note.builder()
                .noteTitle(noteDto.getNoteTitle())
                .noteContent(noteDto.getNoteContent())
                .user(userInfo)
                .build();
        return notesRepository.save(note);
    }

    public Note update(NoteDto noteDto, UUID id, String email) {

        Note note = getById(id,email);
        if (note.getUser().getEmail().equals(email)) {
            note.setNoteTitle(noteDto.getNoteTitle());
            note.setNoteContent(noteDto.getNoteContent());
            return notesRepository.save(note);
        } else throw new SecurityException("Unauthorized");

    }

    public void delete(UUID id, String email) {
        Note note = getById(id,email);
        if (note.getUser().getEmail().equals(email))
            notesRepository.deleteById(id);
        else throw new SecurityException("Unauthorized");
    }
}
