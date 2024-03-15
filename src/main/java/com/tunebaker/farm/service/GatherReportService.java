package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.model.dto.StatReqDto;

import java.util.List;

public interface GatherReportService {
    GatherResponseDto postGatherReport(GatherReportDto gatherReportDto);
    List<GatherReportDto> getStat(StatReqDto statReqDto);
    List<GatherReportDto> getStatByUser(StatReqDto statReqDto, Long userId);
    GatherResponseDto getSum(StatReqDto statReqDto);
    GatherResponseDto getSumByUser(StatReqDto statReqDto, Long userId);
}
