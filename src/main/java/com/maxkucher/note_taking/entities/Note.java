package com.maxkucher.note_taking.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String noteTitle;

    @Column(nullable = false)
    private String noteContent;


    @Column(updatable = false)
    private Date createdDate;

    private Date lastModified;

    @PreUpdate
    private void onPreUpdate() {
        this.lastModified = new Date();
    }

    @PrePersist
    private void onPrePersist() {
        this.createdDate = new Date();
        this.lastModified = new Date();
    }
}
