package com.case4.service.admin;

import com.case4.model.User;

import java.util.Optional;

public interface IAdminService {


    Iterable<User> findAll();

    Optional<User> findById(Long id);

    void save(User t);

    void remove(Long id);
}
