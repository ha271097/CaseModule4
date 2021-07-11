package com.case4.controller;

import com.case4.model.Order;
import com.case4.model.Transaction;
import com.case4.service.order.OrderService;
import com.case4.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
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
        transaction.setCreateDate(LocalDate.now());
        transaction.setTotalAmount(order1.get().getProduct().getPrice());
        orderService.remove(order.getId());
        transactionService.save(transaction);

        return new  ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/payOrder/{id}")
//    public ModelAndView showFormPay(@PathVariable Long id){
//        Optional<Order> order = orderService.findById(id);
//        Transaction transaction = new Transaction();
//        transaction.setOrder(order.get());
//        return new ModelAndView("/orders/payOrder","transaction", transaction);
//    }
//
//    @PostMapping("/payOrder")
//    public ModelAndView payOrder(Transaction transaction){
////        transaction.setOrder(orderService.findById(transaction.getOrder().getId()));
////        Order order = transaction.getOrder();
//        transactionService.save(transaction);
//        orderService.remove(transaction.getOrder().getId());
//        return new ModelAndView("/orders/order");
//    }


}
