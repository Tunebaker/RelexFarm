package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class DailyNormDto {
    private final long productId;
    private final long userId;
    private final float norm;
}
