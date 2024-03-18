package com.tunebaker.farm.repository;

import com.tunebaker.farm.model.entity.WorkerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRatingRepository extends JpaRepository<WorkerRating, Long> {
    List<WorkerRating> findByUserId(Long userId);
}
