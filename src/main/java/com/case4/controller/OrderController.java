package com.case4.controller;

import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.service.order.OrderService;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;


    @GetMapping()
    public ResponseEntity<Iterable<Order>> listOrder(){
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/add/{id}")
    public ResponseEntity<?> addToCart(@PathVariable Long id, @RequestBody Order order){
        Optional<Product> product = productService.findById(id);
        orderService.save(order);
        product.get().setQuantity(product.get().getQuantity()-1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
