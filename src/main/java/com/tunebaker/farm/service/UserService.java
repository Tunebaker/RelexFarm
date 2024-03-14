package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.RegisterUserDto;
import com.tunebaker.farm.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    void createNewUser(RegisterUserDto userDto);

}
