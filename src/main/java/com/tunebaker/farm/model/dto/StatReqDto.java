package com.tunebaker.farm.model.dto;

import com.tunebaker.farm.util.time.Period;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StatReqDto {
    @Enumerated(EnumType.STRING)
    Period period;
    Long productId;
}
