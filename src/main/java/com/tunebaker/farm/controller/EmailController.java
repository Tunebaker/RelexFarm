package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.entity.Product;
import com.tunebaker.farm.service.GatherReportService;
import com.tunebaker.farm.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
@Slf4j
public class EmailController {
    private final GatherReportService service;
    private final KafkaProducerService kafka;

    @GetMapping
    public ResponseEntity<Map<Product, Float>> sendEmail() {

        Map<Product, Float> report = service.generateReport();

        kafka.sendDaySum(report);
        return ResponseEntity.ok(report);

    }
}
