package com.case4.service.category;

import com.case4.model.Category;
import com.case4.model.Product;

import java.util.Optional;

public interface ICategoryService {


    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    void save(Category category);

    void remove(Long id);
}
