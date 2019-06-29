package com.maxkucher.note_taking.repositories;

import com.maxkucher.note_taking.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotesRepository extends JpaRepository<Note, UUID> {

    List<Note> findAllByUser_Email(String email);
}
