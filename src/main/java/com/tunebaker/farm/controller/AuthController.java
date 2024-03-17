package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.AuthDto;
import com.tunebaker.farm.service.UserService;
import com.tunebaker.farm.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Контроллер аутентификации", description = "Содержит операции с аутентификацией пользователей")
public class AuthController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Создать токен авторизации",
               description = "Создаёт токен авторизации для зарегистрированного пользователя")
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthDto authDto) {
        log.info("Получен запрос на авторизацию с email: {}", authDto.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));

        String token = jwtTokenUtil.generateToken(userService.findByEmail(authDto.getEmail()));

        return ResponseEntity.ok(token);
    }

}
