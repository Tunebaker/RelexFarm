package com.tunebaker.farm.util.time;

import com.tunebaker.farm.model.dto.StatReqDto;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TimeUtil {

    public static LocalDateTime getStart(LocalDate periodStart) {
        return periodStart.atStartOfDay();
    }

    public static LocalDateTime getEnd(Period period, LocalDate periodStart) {
        switch (period) {
            case DAY -> {
                return periodStart.plusDays(1).atStartOfDay();
            }
            case WEEK -> {
                return periodStart.plusDays(7).atStartOfDay();
            }
            case MONTH -> {
                return periodStart.plusMonths(1).atStartOfDay();
            }
            case YEAR -> {
                return periodStart.plusYears(1).atStartOfDay();
            }
            default -> throw new RuntimeException("Неизвестное значение Period");
        }
    }
}
