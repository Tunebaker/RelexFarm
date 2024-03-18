package com.tunebaker.farm.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class RegisterUserDto {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String passwordRepeat;
}
