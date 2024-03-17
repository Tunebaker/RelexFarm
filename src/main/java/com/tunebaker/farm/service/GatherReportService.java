package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.dto.GatherReportResponseDto;
import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.util.time.Period;

import java.time.LocalDate;
import java.util.List;

public interface GatherReportService {
    GatherResponseDto postGatherReport(GatherReportDto gatherReportDto);
    List<GatherReportResponseDto> getFarmStat(Period period, LocalDate periodStart, Long productId);
    List<GatherReportResponseDto> getUserStat(Period period, LocalDate periodStart, Long productId, Long userId);
    GatherResponseDto getFarmSum(Period period, LocalDate periodStart, Long productId);
    GatherResponseDto getUserSum(Period period, LocalDate periodStart, Long productId, Long userId);
}
