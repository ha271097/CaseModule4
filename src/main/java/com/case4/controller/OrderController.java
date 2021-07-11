package com.case4.controller;

import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.model.User;
import com.case4.service.order.OrderService;
import com.case4.service.product.ProductService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("/listOrders")
    public ModelAndView listOrder(){
       User user = getPrincipal();
        return new ModelAndView("/orders/order","cart",orderService.findAllByUser(user));
    }

    @ModelAttribute("user")
    public User getPrincipal() {
        User userInfo = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userInfo = userService.getUserByName(((UserDetails) principal).getUsername());
        }
        return userInfo;
    }


//    @ModelAttribute("user")
//    public User user(){
//        User user = new User();
//        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        user = userService.getUserByName(((UserDetails ) obj).getUsername());
//
//        return user;
//    }







    @PostMapping
    public ResponseEntity<?>  addToCart(@RequestBody Order order){
        order.setUser(getPrincipal());
        order.setQuantity(1);
        orderService.save(order);
        Optional<Product> product =  productService.findById(order.getProduct().getId());
        product.get().setQuantity(product.get().getQuantity() -1);
        productService.save(product.get());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


//    @GetMapping("/clear/{id}")
//    public ModelAndView clearCart(HttpSession session) {
//        orderService.deleteAll();
//        session.removeAttribute("cart");
//        return new ModelAndView("/orders/order");
//    }






    @DeleteMapping
    public ResponseEntity<?> deleteOrder(@RequestBody Order order){
        orderService.remove(order.getId());
        return new  ResponseEntity<>(HttpStatus.OK);
    }



    private int isExists(Long id, List<Order> carts) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProduct().getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

}
