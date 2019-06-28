package com.maxkucher.note_taking.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class NoteDto {

    @Size(max = 50)
    private String noteTitle;

    private String noteContent;
}
