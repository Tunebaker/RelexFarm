package com.tunebaker.farm.util.time;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Data
public class TimeUtil {
    public static boolean isDateTimeWithinPeriod(LocalDateTime dateTime, Period period) {
        TimeInterval timeInterval = getTimeInterval(period);
        return dateTime.isAfter(timeInterval.getStart()) && dateTime.isBefore(timeInterval.getEnd());
    }

    private static TimeInterval getTimeInterval(Period period) {
        LocalDate now = LocalDate.now();
        LocalDateTime start;
        LocalDateTime end;

        switch (period) {
            case DAY -> {
                start = now.atStartOfDay();
                end = now.plusDays(1).atStartOfDay();
            }
            case WEEK -> {
                start = now.with(DayOfWeek.MONDAY).atStartOfDay();
                end = now.with(DayOfWeek.MONDAY).plusDays(7).atStartOfDay();
            }
            case MONTH -> {
                start = now.withDayOfMonth(1).atStartOfDay();
                end = now.withDayOfMonth(1).plusMonths(1).atStartOfDay();
            }
//            case YEAR -> {}
            default -> {
                start = LocalDateTime.MIN;
                end = LocalDateTime.MIN;
            }
        }
        return new TimeInterval(start, end);
    }
}
