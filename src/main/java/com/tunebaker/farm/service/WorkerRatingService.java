package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.WorkerRatingDto;

import java.util.List;

public interface WorkerRatingService {
    void addRating(WorkerRatingDto workerRatingDto);
    List<WorkerRatingDto> getRating(Long userId);
}
