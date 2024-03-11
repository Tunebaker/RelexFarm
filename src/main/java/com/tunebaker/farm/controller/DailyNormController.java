package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.DailyNormDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class DailyNormController {

    @PutMapping
    public ResponseEntity<?> putNorm(@RequestBody DailyNormDto dailyNormDto) {
        log.info("DailyNormDto received: {}", dailyNormDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user/{user_id}/product/{product_id}")
    public ResponseEntity<?> getNorm(@PathVariable long product_id, @PathVariable long user_id) {
        log.info("Gather norm requested for user id={}, product id={}", user_id, product_id);
        return ResponseEntity.ok().build();
    }
}
