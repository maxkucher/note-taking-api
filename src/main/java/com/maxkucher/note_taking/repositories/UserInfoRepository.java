package com.maxkucher.note_taking.repositories;

import com.maxkucher.note_taking.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findByEmail(String email);
}
