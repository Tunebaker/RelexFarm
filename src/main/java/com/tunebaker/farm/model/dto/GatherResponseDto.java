package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class GatherResponseDto {
    private final double quantity;
}
