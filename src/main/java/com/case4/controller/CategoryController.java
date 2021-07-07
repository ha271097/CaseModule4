package com.case4.controller;


import com.case4.model.Category;
import com.case4.model.Product;
import com.case4.service.category.CategoryService;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Iterable<Category>> findAll(){

        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> findByID(@PathVariable Long id){

        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveCategory(@RequestBody Category category) {
        categoryService.save(category);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateCategory(@PathVariable Long id, @RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteProduct(@PathVariable Long id){
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
