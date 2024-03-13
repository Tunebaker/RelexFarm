package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.GatherReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gather-report")
@RequiredArgsConstructor
@Slf4j
public class GatherReportController {

    @PreAuthorize("hasAnyRole('WORKER')")
    @PostMapping("/add")
    public ResponseEntity<?> addReport(@RequestBody GatherReportDto gatherReportDto) {
        log.info("GatherReportDto received: {}", gatherReportDto);
        // возвращать сколько осталось до нормы
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('WORKER', 'ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getDayResult(@PathVariable long userId) {
        log.info("Day result requested for user id: {}", userId);
        return ResponseEntity.ok().build();
    }
}
