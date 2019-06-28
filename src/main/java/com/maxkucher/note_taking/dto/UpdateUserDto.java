package com.maxkucher.note_taking.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserDto {
    private String email;
    private String firstName;
    private String lastName;
}
