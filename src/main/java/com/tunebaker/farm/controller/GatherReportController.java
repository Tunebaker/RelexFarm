package com.tunebaker.farm.controller;

import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.dto.GatherReportResponseDto;
import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.service.GatherReportService;
import com.tunebaker.farm.util.time.Period;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gather-report")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Контроллер отчетов о выработке", description = "Содержит операции с отчётами о выработке")
@SecurityRequirement(name = "Bearer Authentication")
public class GatherReportController {

    private final GatherReportService gatherReportService;

    @Operation(summary = "Добавление отчёта", description = "Добавляет новый отчёт о выработке продукта работником")
    @PreAuthorize("hasAnyRole('WORKER', 'ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addReport(@RequestBody GatherReportDto gatherReportDto) {
        log.info("GatherReportDto received: {}", gatherReportDto);
        GatherResponseDto gatherResponseDto = gatherReportService.postGatherReport(gatherReportDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gatherResponseDto);
    }

    @Operation(summary = "Статистика отчётов в общем по ферме для одного вида продукции", description =
            "Возвращает список отчётов в общем по ферме для конкретного вида продукции за заданный период")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<GatherReportResponseDto>> getFarmStat(@PathVariable Long productId,
                                                                     @RequestParam Period period,
                                                                     @RequestParam LocalDate periodStart) {
        List<GatherReportResponseDto> stat = gatherReportService.getFarmStat(period, periodStart, productId);
        return ResponseEntity.ok(stat);
    }

    @Operation(summary = "Статистика отчётов работника для одного вида продукции",
               description = "Возвращает список отчётов конкретного работника для конкретного вида продукции " +
                       "за заданный период")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/product/{productId}/user/{userId}")
    public ResponseEntity<List<GatherReportResponseDto>> getUserStat(@PathVariable Long productId,
                                                                     @PathVariable Long userId,
                                                                     @RequestParam Period period,
                                                                     @RequestParam LocalDate periodStart) {
        List<GatherReportResponseDto> stat = gatherReportService.getUserStat(period, periodStart, productId, userId);
        return ResponseEntity.ok(stat);
    }

    @Operation(summary = "Суммарная выработка одного вида продукции по всей ферме",
               description = "Возвращает суммарную выработку фермы конкретного вида продукции за заданный период")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/sum")
    public ResponseEntity<GatherResponseDto> getFarmSum(@RequestParam Period period,
                                                        @RequestParam LocalDate periodStart,
                                                        @RequestParam Long productId) {
        GatherResponseDto sum = gatherReportService.getFarmSum(period, periodStart, productId);
        return ResponseEntity.ok(sum);
    }

    @Operation(summary = "Суммарная выработка одного вида продукции одним работником ",
               description = "Возвращает суммарную выработку конкретного пользователя для конкретного вида" +
               " продукции за заданный период")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/sum/user/{userId}")
    public ResponseEntity<GatherResponseDto> getUserSum(@RequestParam Period period,
                                                        @RequestParam LocalDate periodStart,
                                                        @RequestParam Long productId,
                                                        @PathVariable Long userId) {
        GatherResponseDto sumByUser = gatherReportService.getUserSum(period, periodStart, productId, userId);
        return ResponseEntity.ok(sumByUser);
    }
}
