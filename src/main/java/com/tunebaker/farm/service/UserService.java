package com.tunebaker.farm.service;

import com.tunebaker.farm.model.dto.RegisterUserDto;
import com.tunebaker.farm.model.entity.User;
import com.tunebaker.farm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private static final String USER_NOT_FOUND = "Пользователь с email '%s' не найден";
    private static final String WORKER_ROLE = "WORKER";
    private final UserRepository userRepository;
//    private final RoleService roleService;

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new RuntimeException(String.format(USER_NOT_FOUND, email))
        );
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        log.info("Found user: {}", user);
        return new org.springframework.security.core.userdetails.User(
                email, user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole().getName())));
    }

    public void createNewUser(RegisterUserDto userDto) {
        // проверка идентичности паролей,
        // преобразование userDto -> user,
        // задание роли WORKER_ROLE, статуса ACTIVE,
        // save to DB
    }
}
