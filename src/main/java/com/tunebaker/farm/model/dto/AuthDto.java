package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class AuthDto {
    private final String email;
    private final String password;
}
