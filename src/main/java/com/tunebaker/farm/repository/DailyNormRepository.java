package com.tunebaker.farm.repository;

import com.tunebaker.farm.model.entity.DailyNorm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyNormRepository extends JpaRepository<DailyNorm, Long> {
    DailyNorm findFirstByUserIdAndProductId(Long userId, Long productId);
}
