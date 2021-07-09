package com.case4.controller;

import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.service.order.OrderService;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping ("/addtocart/{id}")
    public ModelAndView showFormAddToCart(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        Order order = new Order();
        order.setProduct(product.get());
        ModelAndView modelAndView = new ModelAndView("/home/addtocart");
//        modelAndView.addObject("product",product);
        modelAndView.addObject("order", order);
        return modelAndView;
    }





    @PostMapping("/addtocart")
    public ModelAndView addToCart(Order order){
        orderService.save(order);
        Optional<Product> product =  productService.findById(order.getProduct().getId());
        product.get().setQuantity(product.get().getQuantity() -1);
        productService.save(product.get());

        return new ModelAndView("");




    }


}
