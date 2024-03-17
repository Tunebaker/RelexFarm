package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@ToString
public class WorkerRatingResponseDto {
    private final float rating;
    private final long userId;
    private final LocalDateTime dateTime;
}
