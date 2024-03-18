package com.tunebaker.farm.service;

import com.tunebaker.farm.model.entity.Role;
import com.tunebaker.farm.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NoSuchElementException("Роль с таким именем не найдена"));
    }
}
