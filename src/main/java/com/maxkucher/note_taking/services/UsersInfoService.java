package com.maxkucher.note_taking.services;


import com.maxkucher.note_taking.dto.CreateUserDto;
import com.maxkucher.note_taking.dto.UpdateUserDto;
import com.maxkucher.note_taking.dto.UserInfoDto;
import com.maxkucher.note_taking.entities.UserInfo;
import com.maxkucher.note_taking.exceptions.UserNotFoundException;
import com.maxkucher.note_taking.repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersInfoService {
    private final UserInfoRepository userInfoRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserInfo get(String email) {
        return userInfoRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public UserInfo get(UUID id) {
        return userInfoRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<UserInfo> getAll() {
        return userInfoRepository
                .findAll();
    }

    public UserInfo create(CreateUserDto userDto) {
        UserInfo userInfo = UserInfo.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder
                        .encode(userDto.getPassword()))
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
        return userInfoRepository.save(userInfo);
    }


    public UserInfo update(String currentEmail, UpdateUserDto updateUserDto) {

        UserInfo userInfo = get(currentEmail);
        userInfo.setEmail(updateUserDto.getEmail());
        userInfo.setFirstName(updateUserDto.getFirstName());
        userInfo.setLastName(updateUserDto.getLastName());
        return userInfoRepository.save(userInfo);
    }

    @Transactional
    public void delete(String email) {
        userInfoRepository.deleteByEmail(email);
    }
}
