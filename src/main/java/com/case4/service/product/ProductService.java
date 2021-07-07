package com.case4.service.product;

import com.case4.model.Product;
import com.case4.repo.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepo repo;

    @Override
    public Iterable<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void save(Product product) {
        repo.save(product);
    }

    @Override
    public void remove(Long id) {
        repo.deleteById(id);
    }
}
