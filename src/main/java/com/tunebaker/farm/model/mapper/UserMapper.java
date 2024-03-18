package com.tunebaker.farm.model.mapper;

import com.tunebaker.farm.model.dto.RegisterUserDto;
import com.tunebaker.farm.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    User fromRegisterUserDto(RegisterUserDto registerUserDto);
}
