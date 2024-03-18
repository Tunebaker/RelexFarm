package com.tunebaker.farm.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class GatherReportResponseDto {
    private final long productId;
    private final long userId;
    private final LocalDateTime dateTime;
    private final float quantity;

}
