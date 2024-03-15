package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.dto.StatReqDto;
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

import java.util.List;

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
        GatherResponseDto gatherResponseDto = gatherReportService.postGatherReport(gatherReportDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gatherResponseDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<GatherReportDto>> getStat(@RequestBody StatReqDto statReqDto) {
        List<GatherReportDto> stat = gatherReportService.getStat(statReqDto);
        return ResponseEntity.ok(stat);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GatherReportDto>> getStatByUser(@RequestBody StatReqDto statReqDto,
                                                               @PathVariable long userId) {
        List<GatherReportDto> stat = gatherReportService.getStatByUser(statReqDto, userId);
        return ResponseEntity.ok(stat);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/sum")
    public ResponseEntity<GatherResponseDto> getSum(@RequestBody StatReqDto statReqDto) {
        GatherResponseDto sum = gatherReportService.getSum(statReqDto);
        return ResponseEntity.ok(sum);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/sum/user/{userId}")
    public ResponseEntity<GatherResponseDto> getSumByUser(@RequestBody StatReqDto statReqDto, @PathVariable long userId) {
        GatherResponseDto sumByUser = gatherReportService.getSumByUser(statReqDto, userId);
        return ResponseEntity.ok(sumByUser);
    }
}
