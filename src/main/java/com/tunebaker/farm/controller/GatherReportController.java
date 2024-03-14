package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.GatherDto;
import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.service.GatherReportService;
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

    private final GatherReportService gatherReportService;

    @PreAuthorize("hasAnyRole('WORKER', 'ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addReport(@RequestBody GatherReportDto gatherReportDto) {
        log.info("GatherReportDto received: {}", gatherReportDto);
        GatherDto gatherDto = gatherReportService.postGatherReport(gatherReportDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gatherDto);
    }

    @PreAuthorize("hasAnyRole('WORKER', 'ADMIN')")
    @GetMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<GatherDto> getDayResult(@PathVariable long userId, @PathVariable long productId) {
        log.info("Day result requested for user id={} and productId={}", userId, productId);
        GatherDto dayResults = gatherReportService.getDayResults(userId, productId);
        return ResponseEntity.ok(dayResults);
    }
}
