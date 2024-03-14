package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.DailyNormDto;
import com.tunebaker.farm.model.entity.DailyNorm;
import com.tunebaker.farm.model.mapper.DailyNormMapper;
import com.tunebaker.farm.repository.DailyNormRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyNormServiceImpl implements DailyNormService {
    private final DailyNormRepository dailyNormRepository;
    private final DailyNormMapper mapper;

    /**
     * @param dailyNormDto
     */
    @Override
    public void addNorm(DailyNormDto dailyNormDto) {
        DailyNorm dailyNorm;
        Optional<DailyNorm> dailyNormOptional =
                dailyNormRepository.findAnyByUserIdAndProductId(dailyNormDto.getUserId(),
                        dailyNormDto.getProductId());
        if (dailyNormOptional.isPresent()) {
            dailyNorm = dailyNormOptional.get();
            dailyNorm.setNorm(dailyNormDto.getNorm());
            log.info("Будет обновлено существующее значение дневной нормы выработки продукта: {}", dailyNormDto.getNorm());
        } else {
            dailyNorm = mapper.toDailyNorm(dailyNormDto);
            log.info("Будет сохранено новое значение дневной нормы выработки продукта: {}", dailyNormDto.getNorm());
        }
        dailyNormRepository.save(dailyNorm);
    }

    /**
     * @param productId
     * @param userId
     * @return dailyNormDto
     */
    @Override
    public DailyNormDto getNorm(Long productId, Long userId) {
        DailyNorm dailyNorm = dailyNormRepository.findAnyByUserIdAndProductId(userId, productId)
                                                 .orElseThrow(() -> new NoSuchElementException(
                                                         "Для данных пользователя и продукта не задана норма " +
                                                                 "выработки"));
        return mapper.toDailyNormDto(dailyNorm);
    }
}
