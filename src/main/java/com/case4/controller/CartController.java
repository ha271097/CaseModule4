package com.case4.controller;


import com.case4.model.*;
import com.case4.service.bill.BillService;
import com.case4.service.billdetails.BillDetailsService;
import com.case4.service.cart.CartService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailsService billDetailsService;


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


    @GetMapping("/payOrder")
    public ModelAndView showFormPayOrder(HttpSession session){
        User user = (User)session.getAttribute("user");

        ModelAndView modelAndView = new ModelAndView("/orders/bills");
        Bill bill = new Bill();
        modelAndView.addObject("bills", bill);
        return modelAndView;
    }
    
    
    @PostMapping("/payOrder")
    public ModelAndView addBill(HttpSession session, @ModelAttribute("bills") Bill bill){

            billService.addBills(bill);
            billService.save(bill);
            HashMap<Long, Cart> cartHashMap = (HashMap<Long, Cart>) session.getAttribute("cart");

        for (Map.Entry<Long, Cart> itemCart: cartHashMap.entrySet()
             ) {
            BillDetail billDetail = new BillDetail();
            billDetail.setBill(bill);
            billDetail.setProduct(itemCart.getValue().getProduct());
            billDetail.setQuantity(itemCart.getValue().getQuantity());
            billDetail.setTotal(itemCart.getValue().getTotalPrice());
            billDetailsService.save(billDetail);
        }
        session.removeAttribute("cart");
        return new ModelAndView("/orders/order");


    }
    
}




//    @DeleteMapping
//    public ResponseEntity<?> deleteOrder(@RequestBody Order order){
//        orderService.remove(order.getId());
//        return new  ResponseEntity<>(HttpStatus.OK);
//    }
