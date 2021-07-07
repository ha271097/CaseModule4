package com.case4.controller;

import com.case4.model.Product;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> findAll(){

        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findByID(@PathVariable Long id){

        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveCategory(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateCustomer(@PathVariable Long id, @RequestBody Product product) {
//        Optional<Product> productOptional = productService.findById(id);
//        if (!productOptional.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        product.setId(productOptional.get().getId());
//        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteCustomer(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.NO_CONTENT);
    }

}
