package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.entity.GatherReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GatherReportMapper {
    GatherReport fromGatherReport(GatherReportDto gatherReportDto);
}
