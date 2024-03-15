package com.tunebaker.farm.repository;

import com.tunebaker.farm.model.entity.GatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatherReportRepository extends JpaRepository<GatherReport, Long> {
    List<GatherReport> findByUserIdAndProductId(Long userId, Long productId);
    List<GatherReport> findByProductId(Long productId);
}
