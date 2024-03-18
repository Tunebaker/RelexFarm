package com.tunebaker.farm.service;

import com.tunebaker.farm.exception.NoDailyNormSetException;
import com.tunebaker.farm.model.dto.GatherReportResponseDto;
import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.entity.DailyNorm;
import com.tunebaker.farm.model.entity.GatherReport;
import com.tunebaker.farm.model.entity.Product;
import com.tunebaker.farm.model.mapper.GatherReportMapper;
import com.tunebaker.farm.repository.DailyNormRepository;
import com.tunebaker.farm.repository.GatherReportRepository;
import com.tunebaker.farm.util.time.Period;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tunebaker.farm.util.time.TimeUtil.getEnd;
import static com.tunebaker.farm.util.time.TimeUtil.getStart;

@Service
@RequiredArgsConstructor
@Slf4j
public class GatherReportServiceImpl implements GatherReportService {
    private final GatherReportRepository gatherReportRepository;
    private final DailyNormRepository dailyNormRepository;
    private final GatherReportMapper mapper;

    @Override
    public GatherResponseDto postGatherReport(GatherReportDto gatherReportDto) {
        GatherReport gatherReport = mapper.toGatherReport(gatherReportDto);
        gatherReportRepository.save(gatherReport);

        long userId = gatherReportDto.getUserId();
        long productId = gatherReportDto.getProductId();
        double todaySum = getTodayUserSum(productId, userId);

        DailyNorm dailyNorm = dailyNormRepository.findAnyByUserIdAndProductId(userId, productId)
                                                 .orElseThrow(() -> new NoDailyNormSetException(
                                                         "Не задана норма выработки"));
        double needToGather = dailyNorm.getNorm() - todaySum;
        return new GatherResponseDto(needToGather);
    }

    @Override
    public List<GatherReportResponseDto> getFarmStat(Period period, LocalDate periodStart, Long productId) {
        LocalDateTime startDateTime = getStart(periodStart);
        LocalDateTime endDateTime = getEnd(period, periodStart);
        return gatherReportRepository.findByProductIdAndDateTimeBetween(productId, startDateTime, endDateTime)
                                     .stream()
                                     .map(mapper::toGatherReportResponseDto)
                                     .toList();
    }


    @Override
    public List<GatherReportResponseDto> getUserStat(Period period, LocalDate periodStart, Long productId,
                                                     Long userId) {
        LocalDateTime startDateTime = getStart(periodStart);
        LocalDateTime endDateTime = getEnd(period, periodStart);
        return gatherReportRepository.findByUserIdAndProductIdAndDateTimeBetween(userId, productId, startDateTime,
                endDateTime).stream().map(mapper::toGatherReportResponseDto).toList();
    }


    @Override
    public GatherResponseDto getFarmSum(Period period, LocalDate periodStart, Long productId) {
        double sum = getFarmStat(period, periodStart, productId).stream()
                                                                .mapToDouble(GatherReportResponseDto::getQuantity)
                                                                .sum();
        return new GatherResponseDto(sum);
    }

    @Override
    public GatherResponseDto getUserSum(Period period, LocalDate periodStart, Long productId, Long userId) {
        double sum = getUserStat(period, periodStart, productId, userId).stream()
                                                                        .mapToDouble(
                                                                                GatherReportResponseDto::getQuantity)
                                                                        .sum();
        return new GatherResponseDto(sum);
    }


    @Override
    public Map<Product, Float> generateReport() {

        LocalDate currentDate = LocalDate.now();
        List<GatherReport> filteredReports = gatherReportRepository.findByDateTimeBetween(currentDate.atStartOfDay(),
                currentDate.atTime(LocalTime.MAX));

        Map<Product, Float> groupedReports = filteredReports.stream()
                                                            .collect(Collectors.groupingBy(GatherReport::getProduct,
                                                                    Collectors.summingDouble(
                                                                            GatherReport::getQuantity)))
                                                            .entrySet()
                                                            .stream()
                                                            .collect(Collectors.toMap(Map.Entry::getKey,
                                                                    entry -> entry.getValue().floatValue()));

        return new HashMap<>(groupedReports);
    }


    private double getTodayUserSum(Long productId, Long userId) {
        return getUserSum(Period.DAY, LocalDate.now(), productId, userId).getQuantity();
    }
}
