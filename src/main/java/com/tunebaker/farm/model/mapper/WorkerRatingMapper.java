package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.WorkerRatingDto;
import com.tunebaker.farm.model.dto.WorkerRatingResponseDto;
import com.tunebaker.farm.model.entity.WorkerRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkerRatingMapper {
    @Mapping(source = "userId", target = "user.id")
    WorkerRating toWorkerRating(WorkerRatingDto workerRatingDto);

    @Mapping(target = "userId", source = "user.id")
    WorkerRatingResponseDto toWorkerRatingResponseDto(WorkerRating workerRating);
}
