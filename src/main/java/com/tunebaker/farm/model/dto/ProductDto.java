package com.tunebaker.farm.model.dto;

import com.tunebaker.farm.model.enums.MeasureUnit;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ProductDto {
    private final String name;
    private final MeasureUnit unit;
}
