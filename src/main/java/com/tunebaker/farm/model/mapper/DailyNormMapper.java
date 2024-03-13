package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.DailyNormDto;
import com.tunebaker.farm.model.entity.DailyNorm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyNormMapper {
    DailyNorm fromDailyNormDto(DailyNormDto dailyNormDto);
}
