package com.maxkucher.note_taking.services;


import com.maxkucher.note_taking.repositories.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotesService {
    private NotesRepository notesRepository;
}
