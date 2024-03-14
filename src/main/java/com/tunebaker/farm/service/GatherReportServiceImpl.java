package com.tunebaker.farm.service;

import com.tunebaker.farm.exception.NoDailyNormSetException;
import com.tunebaker.farm.model.dto.GatherDto;
import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.entity.DailyNorm;
import com.tunebaker.farm.model.entity.GatherReport;
import com.tunebaker.farm.model.mapper.GatherReportMapper;
import com.tunebaker.farm.repository.DailyNormRepository;
import com.tunebaker.farm.repository.GatherReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class GatherReportServiceImpl implements GatherReportService {
    private final GatherReportRepository gatherReportRepository;
    private final DailyNormRepository dailyNormRepository;
    private final GatherReportMapper mapper;

    @Override
    public GatherDto postGatherReport(GatherReportDto gatherReportDto) {
        GatherReport gatherReport = mapper.toGatherReport(gatherReportDto);
        gatherReportRepository.save(gatherReport);

        long userId = gatherReportDto.getUserId();
        long productId = gatherReportDto.getProductId();
        GatherDto dayResults = getDayResults(userId, productId);

        DailyNorm dailyNorm = dailyNormRepository.findAnyByUserIdAndProductId(userId, productId)
                                                 .orElseThrow(() -> new NoDailyNormSetException(
                                                         "Не задана норма выработки"));
        double needToGather = dailyNorm.getNorm() - dayResults.getQuantity();
        return new GatherDto(productId, userId, needToGather);
    }

    @Override
    public GatherDto getDayResults(Long userId, Long productId) {
        LocalDate date = LocalDate.now();
        double sum = gatherReportRepository.findByUserIdAndProductId(userId, productId)
                                           .stream()
                                           .filter(report -> isReportForDate(report, date))
                                           .mapToDouble(GatherReport::getQuantity)
                                           .sum();
        return new GatherDto(productId, userId, sum);
    }

    private boolean isReportForDate(GatherReport report, LocalDate localDate) {
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.plusDays(1).atStartOfDay();
        LocalDateTime reportDateTime = report.getDateTime();
        return reportDateTime.isBefore(endOfDay) && reportDateTime.isAfter(startOfDay);
    }
}
