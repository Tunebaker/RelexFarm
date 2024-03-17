package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.RegisterUserDto;
import com.tunebaker.farm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ADMIN')")
@Tag(name = "Контроллер пользователей", description = "Содержит операции с учетными записями пользователей")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Регистрация нового пользователя",
               description = "Регистрирует нового пользователя, присваивает ему статус активного и роль работника")
    @PostMapping("/new")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDto registerUserDto) {
        log.info("Получены данные для регистрации нового пользователя: {}", registerUserDto);
        userService.createNewUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Увольнение работника",
               description = "Присваивает работнику статус уволенного")
    @PatchMapping("/dismiss/{userId}")
    public ResponseEntity<?> dismissUser(@PathVariable (name = "userId") long userId) {
        log.info("Запрошено увольнение работника с id = {}", userId);
        userService.dismiss(userId);
        return ResponseEntity.ok().build();
    }
}
