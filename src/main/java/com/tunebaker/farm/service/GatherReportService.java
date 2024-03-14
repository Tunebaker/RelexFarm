package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.GatherDto;
import com.tunebaker.farm.model.dto.GatherReportDto;

public interface GatherReportService {
    GatherDto postGatherReport(GatherReportDto gatherReportDto);
    GatherDto getDayResults(Long userId, Long productId);
}
