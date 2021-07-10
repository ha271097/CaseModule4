package com.case4.service.role;

import com.case4.model.Role;
import com.case4.repo.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService{

    @Autowired
    private IRoleRepo repo;

    @Override
    public Role findByName(String name){
       return repo.findByRoleName("ROLE_USER");
    }
    @Override
    public Iterable<Role> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void remove(Long id) {

    }
}
