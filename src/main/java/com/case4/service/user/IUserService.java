package com.case4.service.user;

import com.case4.model.Product;
import com.case4.model.User;

import java.util.Optional;

public interface IUserService {

    User getUserByName(String name);

    Iterable<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);

    void remove(Long id);
//    User findOne(String account);
}
