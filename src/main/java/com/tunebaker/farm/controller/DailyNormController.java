package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.DailyNormDto;
import com.tunebaker.farm.service.DailyNormService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/daily-norm")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Контроллер норм выработки", description = "Содержит операции с нормой выработки")
@SecurityRequirement(name = "Bearer Authentication")
public class DailyNormController {

    private final DailyNormService dailyNormService;

    @Operation(summary = "Обновить дневную норму выработки",
               description = "Обновляет (в случае отсутствия создаёт) дневную норму выработки для id пользователя и id продукта")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/new")
    public ResponseEntity<?> putNorm(@RequestBody DailyNormDto dailyNormDto) {
        log.info("DailyNormDto received: {}", dailyNormDto);
        dailyNormService.addNorm(dailyNormDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Получить норму выработки",
               description = "Возвращает дневную норму выработки для id пользователя и id продукта")
    @PreAuthorize("hasAnyRole('WORKER', 'ADMIN')")
    @GetMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<DailyNormDto> getNorm(@PathVariable long productId, @PathVariable long userId) {
        log.info("Gather norm requested for user id={}, product id={}", userId, productId);
        DailyNormDto norm = dailyNormService.getNorm(productId, userId);
        return ResponseEntity.ok(norm);
    }
}
