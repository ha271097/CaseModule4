package com.case4.service.order;

import com.case4.model.Order;
import com.case4.model.Product;

import java.util.Optional;

public interface IOrderService {

    Iterable<Order> findAll();

    Optional<Order> findById(Long id);

    void save(Order order);

    void remove(Long id);

    void deleteAll();
}
