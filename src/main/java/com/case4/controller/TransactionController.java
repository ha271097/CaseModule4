package com.case4.controller;

import com.case4.model.Order;
import com.case4.model.Transaction;
import com.case4.service.order.OrderService;
import com.case4.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> buyOrder(@RequestBody Order order){

        Optional<Order> order1 = orderService.findById(order.getId());
//        System.out.println(order1.get().getUser().getUsername());
        Transaction transaction = new Transaction();
        transaction.setUserName(order1.get().getUser().getName());
        transaction.setTotalAmount(order1.get().getProduct().getPrice());
        orderService.remove(order.getId());
        transactionService.save(transaction);

        return new  ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/payOrder/{id}")
    public ModelAndView payOrder(@PathVariable Long id){
        Optional<Order> order = orderService.findById(id);
        Transaction transaction = new Transaction();
        transaction.setOrder(order.get());
        return new ModelAndView("/orders/payOrder","transaction", transaction);
    }


}
