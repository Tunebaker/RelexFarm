package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.WorkerRatingDto;
import com.tunebaker.farm.model.dto.WorkerRatingResponseDto;

import java.util.List;

public interface WorkerRatingService {
    void addRating(WorkerRatingDto workerRatingDto);
    List<WorkerRatingResponseDto> getRating(Long userId);
}
