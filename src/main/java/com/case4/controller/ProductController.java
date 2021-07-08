package com.case4.controller;

import com.case4.model.Product;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ModelAndView listProduct(){
        return new ModelAndView("/products/index", "list", productService.findAll());
    }

//    @GetMapping()
//    public ResponseEntity<Iterable<Product>> findAll(){
//
//        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/{id}/edit")
    public ModelAndView showFormEdit(@PathVariable Long id){
        return new ModelAndView("/products/edit", "customer", productService.findById(id));

    }

    @PostMapping("/edit")
    public ModelAndView edit(Product product){
        productService.save(product);
        return new ModelAndView("redirect:/products/list");
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findByID(@PathVariable Long id){

        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        productService.save(product);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        productService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
