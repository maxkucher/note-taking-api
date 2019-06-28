package com.maxkucher.note_taking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;

    private String lastName;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Note> notes;
}
