package com.tunebaker.farm.service;

import com.tunebaker.farm.exception.PasswordsNotEqualException;
import com.tunebaker.farm.exception.UserAlreadyExistsException;
import com.tunebaker.farm.model.dto.RegisterUserDto;
import com.tunebaker.farm.model.entity.User;
import com.tunebaker.farm.model.enums.ActivityStatus;
import com.tunebaker.farm.model.mapper.UserMapper;
import com.tunebaker.farm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String USER_NOT_FOUND = "Пользователь с email '%s' не найден";
    private static final String DEFAULT_ROLE = "ROLE_WORKER";
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email)
                             .orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND, email)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        log.info("Найден пользователь: {}", user);
        if(user.getStatus().equals(ActivityStatus.DISMISSED)) {
            throw new DisabledException("Пользователь отключён");
        }
        return new org.springframework.security.core.userdetails.User(email, user.getPassword(),
                Set.of(new SimpleGrantedAuthority(user.getRole().getName())));
    }

    public void createNewUser(RegisterUserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getPasswordRepeat())) {
            throw new PasswordsNotEqualException("Введённые пароли не совпадают");
        }

        if (userRepository.findUserByEmail(userDto.getEmail()).isEmpty()) {
            User user = userMapper.fromRegisterUserDto(userDto)
                                  .setPassword(encoder.encode(userDto.getPassword()))
                                  .setStatus(ActivityStatus.ACTIVE)
                                  .setRole(roleService.getByName(DEFAULT_ROLE));
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("Пользователь с таким email уже зарегистрирован");
        }
    }
}
