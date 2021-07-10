package com.case4.service.order;

import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.model.User;

import java.util.Optional;

public interface IOrderService {

    Iterable<Order> findAll();

    Iterable<Order> findAllByUser(User user);

    Optional<Order> findById(Long id);

    void save(Order order);

    void remove(Long id);

    void deleteAll();
}
