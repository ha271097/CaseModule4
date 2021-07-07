package com.case4.service.product;

import com.case4.model.Product;
import com.case4.model.User;

import java.util.Optional;

public interface IProductService {

    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void remove(Long id);
}
