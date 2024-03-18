package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class WorkerRatingDto {
    private final float rating;
    private final long userId;

    public WorkerRatingDto(float rating, long userId) {
        this.rating = rating;
        this.userId = userId;
    }
}
