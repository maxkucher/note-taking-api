package com.maxkucher.note_taking.controllers;

import com.maxkucher.note_taking.services.UsersInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing user-related endpoints
 */
@RestController
@RequiredArgsConstructor
public class UsersInfoController {
    private final UsersInfoService usersInfoService;
}
