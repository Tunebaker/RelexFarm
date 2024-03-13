package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.RatingDto;
import com.tunebaker.farm.model.entity.WorkerRating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerRatingMapper {
    WorkerRating fromRatingDto(RatingDto ratingDto);
}
