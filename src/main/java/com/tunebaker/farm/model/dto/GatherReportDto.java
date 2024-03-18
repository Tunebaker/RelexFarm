package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class GatherReportDto {
    private final long productId;
    private final long userId;
    private final float quantity;

    public GatherReportDto(long productId, long userId, float quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }
}
