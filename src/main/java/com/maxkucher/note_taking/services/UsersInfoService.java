package com.maxkucher.note_taking.services;


import com.maxkucher.note_taking.dto.UserInfoDto;
import com.maxkucher.note_taking.entities.UserInfo;
import com.maxkucher.note_taking.exceptions.UserNotFoundException;
import com.maxkucher.note_taking.repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersInfoService {
    private final UserInfoRepository userInfoRepository;

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


    public UserInfo update(UUID id, UserInfoDto userInfoDto) {
        UserInfo userInfo = get(id);
        userInfo.setEmail(userInfoDto.getEmail());
        userInfo.setFirstName(userInfoDto.getFistName());
        userInfo.setLastName(userInfoDto.getLastName());
        return userInfoRepository.save(userInfo);
    }

    public void delete(UUID id) {
        userInfoRepository.deleteById(id);
    }
}
