package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.WorkerRatingDto;
import com.tunebaker.farm.model.entity.WorkerRating;
import com.tunebaker.farm.model.mapper.WorkerRatingMapper;
import com.tunebaker.farm.repository.WorkerRatingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkerRatingServiceImpl implements WorkerRatingService {
    private final WorkerRatingRepository workerRatingRepository;
    private final WorkerRatingMapper mapper;

    /**
     * @param workerRatingDto
     */
    @Override
    public void addRating(WorkerRatingDto workerRatingDto) {
        WorkerRating workerRating = mapper.toWorkerRating(workerRatingDto);
        workerRatingRepository.save(workerRating);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<WorkerRatingDto> getRating(Long userId) {
        List<WorkerRating> ratings = workerRatingRepository.findByUserId(userId);
        return ratings.stream().map(mapper::toWorkerRatingDto).toList();
    }
}
