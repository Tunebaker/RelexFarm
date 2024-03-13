package com.tunebaker.farm.service;

import com.tunebaker.farm.exception.PasswordsNotEqualException;
import com.tunebaker.farm.exception.UserAlreadyExistsException;
import com.tunebaker.farm.model.dto.RegisterUserDto;
import com.tunebaker.farm.model.entity.User;
import com.tunebaker.farm.model.enums.ActivityStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface UserService {
    User findByEmail(String email);

    void createNewUser(RegisterUserDto userDto);

}
