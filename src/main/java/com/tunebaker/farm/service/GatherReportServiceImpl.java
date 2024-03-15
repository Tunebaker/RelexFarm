package com.tunebaker.farm.service;

import com.tunebaker.farm.exception.NoDailyNormSetException;
import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.entity.DailyNorm;
import com.tunebaker.farm.model.entity.GatherReport;
import com.tunebaker.farm.model.mapper.GatherReportMapper;
import com.tunebaker.farm.repository.DailyNormRepository;
import com.tunebaker.farm.repository.GatherReportRepository;
import com.tunebaker.farm.util.time.Period;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tunebaker.farm.util.time.TimeUtil.isDateTimeWithinPeriod;

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
        double thisDaySum = getThisDaySum(userId, productId);

        DailyNorm dailyNorm = dailyNormRepository.findAnyByUserIdAndProductId(userId, productId)
                                                 .orElseThrow(() -> new NoDailyNormSetException(
                                                         "Не задана норма выработки"));
        double needToGather = dailyNorm.getNorm() - thisDaySum;
        return new GatherResponseDto(productId, userId, needToGather);
    }

    @Override
    public List<GatherReportDto> getStat(Period period, Long productId) {
        return gatherReportRepository.findByProductId(productId).stream()
                .filter(report -> isDateTimeWithinPeriod(report.getDateTime(), period))
                .map(mapper::toGatherReportDto)
                .toList();
    }

    @Override
    public List<GatherReportDto> getStatByUser(Period period, Long productId, Long userId) {
        return gatherReportRepository.findByUserIdAndProductId(userId, productId).stream()
                .filter(report -> isDateTimeWithinPeriod(report.getDateTime(), period))
                .map(mapper::toGatherReportDto)
                .toList();
    }

    private double getThisDaySum(long userId, long productId) {
        return getStatByUser(Period.DAY, productId, userId).stream()
                                                           .mapToDouble(GatherReportDto::getQuantity)
                                                           .sum();
    }
}
