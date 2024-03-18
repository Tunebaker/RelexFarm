package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.DailyNormDto;

public interface DailyNormService {
    void addNorm(DailyNormDto dailyNormDto);
    DailyNormDto getNorm(Long productId, Long userId);
}
