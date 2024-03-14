package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.WorkerRatingDto;
import com.tunebaker.farm.service.WorkerRatingService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
@Slf4j
public class WorkerRatingController {

    private final WorkerRatingService workerRatingService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> addRating(@RequestBody WorkerRatingDto workerRatingDto) {
        log.info("workerRatingDto received: {}", workerRatingDto);
        workerRatingService.addRating(workerRatingDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('WORKER', 'ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkerRatingDto>> getRating(@PathVariable long userId) {
        log.info("Rating requested for user id={}", userId);
        List<WorkerRatingDto> workerRatingDtos = workerRatingService.getRating(userId);
        return ResponseEntity.ok(workerRatingDtos);
    }
}
