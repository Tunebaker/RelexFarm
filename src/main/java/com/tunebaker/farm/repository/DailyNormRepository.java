package com.tunebaker.farm.repository;

import com.tunebaker.farm.model.entity.DailyNorm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyNormRepository extends JpaRepository<DailyNorm, Long> {
    Optional<DailyNorm> findAnyByUserIdAndProductId(Long userId, Long productId);
}
