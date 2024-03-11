package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.AuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @PostMapping("/auth")
    public ResponseEntity<?> authorize(@RequestBody AuthDto authDto) {
        log.info("Received auth request with email: {}", authDto.getEmail());
        return ResponseEntity.ok().build();
    }

}
