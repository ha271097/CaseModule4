package com.case4.repo;

import com.case4.model.Order;
import com.case4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOderRepo extends JpaRepository<Order,Long> {
    Iterable<Order> getAllByUser(User user);
}
