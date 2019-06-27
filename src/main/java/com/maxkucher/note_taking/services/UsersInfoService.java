package com.maxkucher.note_taking.services;


import com.maxkucher.note_taking.repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersInfoService {
    private final UserInfoRepository userInfoRepository;
}
