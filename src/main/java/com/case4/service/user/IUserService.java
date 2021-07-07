package com.case4.service.user;

import com.case4.model.User;

import java.util.Optional;

public interface IUserService {
    User getUserByName(String name);

}
