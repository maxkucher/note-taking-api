package com.maxkucher.note_taking.controllers;

import com.maxkucher.note_taking.dto.CreateUserDto;
import com.maxkucher.note_taking.dto.NoteTakerResponse;
import com.maxkucher.note_taking.dto.UpdateUserDto;
import com.maxkucher.note_taking.dto.UserInfoDto;
import com.maxkucher.note_taking.services.UsersInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller for managing user-related endpoints
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersInfoController {
    private final UsersInfoService usersInfoService;

    @PreAuthorize("#email==authentication.name")
    @GetMapping
    public ResponseEntity<?> get(@RequestParam() String email) {


        //TODO uncomment after adding roles
        return ResponseEntity.ok(new UserInfoDto(
                usersInfoService.get(email)));
        /*return ResponseEntity.ok(usersInfoService
                .getAll()
                .stream()
                .map(UserInfoDto::new)
                .collect(Collectors.toList()));*/
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(new UserInfoDto(usersInfoService.create(createUserDto)));
    }

    @PreAuthorize("#currentEmail == authentication.name")
    @PutMapping("/{currentEmail}")
    public ResponseEntity<?> update(@PathVariable String currentEmail,
                                    @Valid @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(new UserInfoDto(usersInfoService
                .update(currentEmail, updateUserDto)));
    }


    @PreAuthorize("#email == authentication.name")
    @DeleteMapping("/{email}")
    public ResponseEntity<?> delete(@PathVariable String email) {
        usersInfoService.delete(email);
        return ResponseEntity.ok(new NoteTakerResponse(true,
                "User was deleted"));
    }
}
