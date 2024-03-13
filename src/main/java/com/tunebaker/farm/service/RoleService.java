package com.tunebaker.farm.service;

import com.tunebaker.farm.model.entity.Role;

public interface RoleService {
    /**
     * @param name  String parameter used for search
     * @return Role
     */
    Role getByName(String name);
}
