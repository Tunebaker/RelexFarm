package com.tunebaker.farm.util.time;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeInterval {
    private final LocalDateTime start;
    private final LocalDateTime end;
}
