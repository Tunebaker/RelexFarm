package com.tunebaker.farm.repository;

import com.tunebaker.farm.model.entity.GatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GatherReportRepository extends JpaRepository<GatherReport, Long> {
    List<GatherReport> findByUserIdAndProductIdAndDateTimeBetween(Long userId, Long productId, LocalDateTime start,
                                                                  LocalDateTime end);
    List<GatherReport> findByProductIdAndDateTimeBetween(Long productId, LocalDateTime start, LocalDateTime end);
}
