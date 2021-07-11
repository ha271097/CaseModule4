package com.case4.service.order;

import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.model.User;
import com.case4.repo.IOderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
    public void deleteByProductAndUser(Long id_product, Long id_user) {
        oderRepo.removeByProductAndUser(id_product,id_user);
    }

    @Override
    public Iterable<Order> findAllByUser(User user) {
        return oderRepo.getAllByUser(user);
    }

//    @Override
//    public Iterable<Order> findAllByUserId(Long id) {
//        return oderRepo.findAllById(Collections.singleton(id));
//    }

    @Override
    public Optional<Order> findById(Long id) {
        return oderRepo.findById(id);
    }

    @Override
    public void save(Order order) {
        oderRepo.save(order);
    }

    @Override
    public void remove(Long id) {
        oderRepo.deleteById(id);
    }

    @Override
    public void deleteAllByUser(User user) {
        oderRepo.deleteAllByUser(user);
    }


}
