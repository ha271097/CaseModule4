package com.case4.service.role;

import com.case4.model.Product;
import com.case4.model.Role;

import java.util.Optional;

public interface IRoleService {

    Iterable<Role> findAll();

    Optional<Role> findById(Long id);

    void save(Role role);

    void remove(Long id);

    Role findByName(String name);
}
