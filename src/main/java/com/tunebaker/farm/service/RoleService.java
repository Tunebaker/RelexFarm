package com.tunebaker.farm.service;

import com.tunebaker.farm.model.entity.Role;

public interface RoleService {
    /**
     * @param name used for Role search
     * @return Role
     */
    Role getByName(String name);
}
