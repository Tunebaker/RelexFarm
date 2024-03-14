package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GatherDto {
    private final long productId;
    private final long userId;
    private final double quantity;

    public GatherDto(long productId, long userId, double quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }
}
