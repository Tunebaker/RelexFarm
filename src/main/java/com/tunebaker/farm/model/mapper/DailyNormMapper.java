package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.DailyNormDto;
import com.tunebaker.farm.model.entity.DailyNorm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DailyNormMapper {
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "userId", target = "user.id")
    DailyNorm toDailyNorm(DailyNormDto dailyNormDto);
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    DailyNormDto toDailyNormDto(DailyNorm dailyNorm);
}
