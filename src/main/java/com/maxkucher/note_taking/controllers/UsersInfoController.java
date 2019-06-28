package com.maxkucher.note_taking.controllers;

import com.maxkucher.note_taking.dto.CreateUserDto;
import com.maxkucher.note_taking.dto.NoteTakerResponse;
import com.maxkucher.note_taking.dto.UpdateUserDto;
import com.maxkucher.note_taking.dto.UserInfoDto;
import com.maxkucher.note_taking.entities.UserInfo;
import com.maxkucher.note_taking.services.UsersInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller for managing user-related endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersInfoController {
    private final UsersInfoService usersInfoService;

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) String email,
                                 @RequestParam(required = false) UUID id) {
        if (Objects.nonNull(id)) {
            return ResponseEntity.ok(new UserInfoDto(
                    usersInfoService.get(id)));
        }

        if (Objects.nonNull(email)) {
            return ResponseEntity.ok(new UserInfoDto(
                    usersInfoService.get(email)));
        }

        return ResponseEntity.ok(usersInfoService
                .getAll()
                .stream()
                .map(UserInfoDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(usersInfoService.create(createUserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,
                                    @Valid @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(new UserInfoDto(usersInfoService
                .update(id, updateUserDto)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        usersInfoService.delete(id);
        return ResponseEntity.ok(new NoteTakerResponse(true,
                "User was deleted"));
    }
}
