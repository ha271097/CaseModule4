package com.case4.repo;

import com.case4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface IUserRepo extends JpaRepository<User, Long> {
    User findByUsername(String name);

}
