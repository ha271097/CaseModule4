package com.case4.controller;


import com.case4.model.Cart;
import com.case4.model.Order;
import com.case4.model.Product;
import com.case4.model.User;
import com.case4.service.cart.CartService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;


    @ModelAttribute("user")
    public User getPrincipal() {
        User userInfo = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userInfo = userService.getUserByName(((UserDetails) principal).getUsername());
        }
        return userInfo;
    }


    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody Cart cart, HttpSession session) {
        cart.setUser(getPrincipal());
        HashMap<Long, Cart> cartHashMap = (HashMap<Long, Cart>) session.getAttribute("cart");
        if (cartHashMap == null) {
            cartHashMap = new HashMap<Long, Cart>();
        }
        cartHashMap = cartService.addCart(cart.getProduct().getId(), cartHashMap);
        session.setAttribute("cart", cartHashMap);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);


    }


    @DeleteMapping
    public ResponseEntity<?> deleteCart(@RequestBody Cart cart, HttpSession session) {
        cart.setUser(getPrincipal());
        HashMap<Long, Cart> cartHashMap = (HashMap<Long, Cart>) session.getAttribute("cart");
        if (cartHashMap == null) {
            cartHashMap = new HashMap<Long, Cart>();
        }
        cartHashMap = cartService.deleteCart(cart.getProduct().getId(), cartHashMap);
        session.setAttribute("cart", cartHashMap);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


//    @DeleteMapping
//    public ResponseEntity<?> deleteOrder(@RequestBody Order order){
//        orderService.remove(order.getId());
//        return new  ResponseEntity<>(HttpStatus.OK);
//    }
