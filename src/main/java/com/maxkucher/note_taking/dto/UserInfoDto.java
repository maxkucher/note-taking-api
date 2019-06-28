package com.maxkucher.note_taking.dto;


import com.maxkucher.note_taking.entities.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;

    public UserInfoDto(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.email = userInfo.getEmail();
        this.firstName = userInfo.getFirstName();
        this.lastName = userInfo.getLastName();
    }
}
