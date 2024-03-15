package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GatherResponseDto {
    private final long productId;
    private final long userId;
    private final double sum;

    public GatherResponseDto(long productId, long userId, double sum) {
        this.productId = productId;
        this.userId = userId;
        this.sum = sum;
    }
}
