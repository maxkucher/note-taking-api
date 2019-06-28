package com.maxkucher.note_taking.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    @Email
    private String email;
    @Size(min = 5)
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}
