package com.tunebaker.farm.service;

import com.tunebaker.farm.exception.NoDailyNormSetException;
import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.dto.StatReqDto;
import com.tunebaker.farm.model.entity.DailyNorm;
import com.tunebaker.farm.model.entity.GatherReport;
import com.tunebaker.farm.model.mapper.GatherReportMapper;
import com.tunebaker.farm.repository.DailyNormRepository;
import com.tunebaker.farm.repository.GatherReportRepository;
import com.tunebaker.farm.util.time.Period;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        double todaySum = getTodaySumByUser(productId, userId);

        DailyNorm dailyNorm = dailyNormRepository.findAnyByUserIdAndProductId(userId, productId)
                                                 .orElseThrow(() -> new NoDailyNormSetException(
                                                         "Не задана норма выработки"));
        double needToGather = dailyNorm.getNorm() - todaySum;
        return new GatherResponseDto(needToGather);
    }

    @Override
    public List<GatherReportDto> getStat(StatReqDto statReqDto) {
        Long productId = statReqDto.getProductId();
        return gatherReportRepository.findByProductIdAndDateTimeBetween(productId, getStart(statReqDto),
                getEnd(statReqDto))
                                     .stream()
                                     .map(mapper::toGatherReportDto)
                                     .toList();
    }

    @Override
    public List<GatherReportDto> getStatByUser(StatReqDto statReqDto, Long userId) {
        Long productId = statReqDto.getProductId();
        return gatherReportRepository.findByUserIdAndProductIdAndDateTimeBetween(userId, productId, getStart(statReqDto),
                                             getEnd(statReqDto))
                                     .stream()
                                     .map(mapper::toGatherReportDto)
                                     .toList();
    }

    @Override
    public GatherResponseDto getSum(StatReqDto statReqDto) {
        double sum = getStat(statReqDto).stream()
                                             .mapToDouble(GatherReportDto::getQuantity)
                                             .sum();
        return new GatherResponseDto(sum);
    }

    @Override
    public GatherResponseDto getSumByUser(StatReqDto statReqDto, Long userId) {
        double sum = getStatByUser(statReqDto, userId).stream()
                                              .mapToDouble(GatherReportDto::getQuantity)
                                              .sum();
        return new GatherResponseDto(sum);
    }

    private double getTodaySumByUser(Long productId, Long userId) {
        StatReqDto statReqForToday = new StatReqDto(Period.DAY, LocalDate.now(), productId);
        return getStatByUser(statReqForToday, userId).stream()
                                                     .mapToDouble(GatherReportDto::getQuantity)
                                                     .sum();
    }
}
