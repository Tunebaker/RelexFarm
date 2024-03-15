package com.tunebaker.farm.model.dto;

import com.tunebaker.farm.util.time.Period;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@RequiredArgsConstructor
public class StatReqDto {
    @Enumerated(EnumType.STRING)
    private final Period period;
    private final LocalDate periodStart;
    private final Long productId;
}
