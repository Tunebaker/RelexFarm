package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.GatherResponseDto;
import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.util.time.Period;

import java.util.List;

public interface GatherReportService {
    GatherResponseDto postGatherReport(GatherReportDto gatherReportDto);

    List<GatherReportDto> getStat(Period period, Long productId);

    List<GatherReportDto> getStatByUser(Period period, Long productId, Long userId);

}
