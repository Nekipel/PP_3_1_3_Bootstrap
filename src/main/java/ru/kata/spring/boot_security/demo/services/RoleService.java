package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService{
    public Role findRoleById(Long id);

    public List<Role> getAllRoles();

    public void addRole(Role role);
}
