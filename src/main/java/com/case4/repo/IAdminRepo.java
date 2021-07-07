package com.case4.repo;

import com.case4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAdminRepo extends JpaRepository<User, Long> {
}
