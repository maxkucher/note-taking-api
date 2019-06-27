package com.maxkucher.note_taking.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue
    private UUID id;
}
