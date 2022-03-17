package com.kata_3_1_2.PP.service;

import com.kata_3_1_2.PP.entitys.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAll();
    Role getById(Long id);
    Set<Role> getByName(String[] roleName);
    void addRole(Role role);
    void deleteById(Long id);
}
