package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.GatherReportDto;
import com.tunebaker.farm.model.entity.GatherReport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GatherReportMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "userId", target = "user.id")
    GatherReport toGatherReport(GatherReportDto gatherReportDto);
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    GatherReportDto toGatherReportDto(GatherReport gatherReport);
}
