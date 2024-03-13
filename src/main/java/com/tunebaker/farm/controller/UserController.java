package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.RegisterUserDto;
import com.tunebaker.farm.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        log.info("Received registerUserDto: {}", registerUserDto);
        userService.createNewUser(registerUserDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/dismiss/{userId}")
    public ResponseEntity<?> dismissUser(@PathVariable (name = "userId") long userId) {
        log.info("Request dismissing user with id={}", userId);
        return ResponseEntity.ok().build();
    }
}
