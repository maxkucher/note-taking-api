package com.maxkucher.note_taking.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String noteTitle;

    @Column(nullable = false)
    private String noteContent;


    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModified;
}
