package com.case4.service.order;

import com.case4.model.Order;
import com.case4.repo.IOderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOderRepo oderRepo;

    @Override
    public Iterable<Order> findAll() {
        return oderRepo.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return oderRepo.findById(id);
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void remove(Long id) {

    }
}
