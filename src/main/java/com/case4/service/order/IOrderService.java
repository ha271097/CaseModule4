package com.case4.service.order;

import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.model.User;
import org.springframework.data.jdbc.repository.query.Query;

import java.util.Optional;

public interface IOrderService {

    Iterable<Order> findAll();


    void deleteByProductAndUser(Long id_product, Long id_user);

    Iterable<Order> findAllByUser(User user);

    Optional<Order> findById(Long id);

    void save(Order order);

    void remove(Long id);

    void deleteAllByUser(User user);
}
