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

    @GetMapping("listOrders")
    public ModelAndView listOrder(){
        return new ModelAndView("/orders/order","cart",orderService.findAll());
    }

    @ModelAttribute("user")
    private User getPrincipal() {
        User userInfo = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userInfo = userService.getUserByName(((UserDetails) principal).getUsername());
        }
        return userInfo;
    }


    @GetMapping ("/addtocart/{id}")
    public ModelAndView showFormAddToCart(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        Order order = new Order();
        order.setProduct(product.get());
        ModelAndView modelAndView = new ModelAndView("/home/addtocart");
        modelAndView.addObject("order", order);
        return modelAndView;
    }

//    @PostMapping("/addtocart")
//    public ResponseEntity<String> buyAjax( @RequestBody Product product, HttpSession session){
//        if (session.getAttribute("order") == null) {
//            List<Order> orders = new ArrayList<>();
//            orders.add(new Order(Optional.ofNullable(product), 1));
//            session.setAttribute("order", orders);
//        } else {
//            List<Order> orders = (List<Order>) session.getAttribute("cart");
//            int index = isExists(product.getId(), orders);
//            if (index == -1) {
//                orders.add(new Order(productService.findById(product.getId()), 1));
//            } else {
//                int quanlity = orders.get(index).getQuantity() + 1;
//                orders.get(index).setQuantity(quanlity);
//            }
//            session.setAttribute("cart", orders);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @PostMapping("/addtocart")
    public ModelAndView addToCart(Order order){
        order.setQuantity(1);
        orderService.save(order);
        Optional<Product> product =  productService.findById(order.getProduct().getId());
        product.get().setQuantity(product.get().getQuantity() -1);
        productService.save(product.get());
        return new ModelAndView("redirect:");

    }

//    @GetMapping("/clear")
//    public ModelAndView clearCart(HttpSession session) {
//        orderService.deleteAll();
//        session.removeAttribute("cart");
//        return new ModelAndView("/orders/order");
//    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearOrder(){
        orderService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/save")
//    public ModelAndView saveCart(HttpSession session) {
//        List<Order> carts = (List<Order>) session.getAttribute("cart");
//        Transaction transaction = new Transaction();
//        transaction.setUser(getPrincipal().getName());
//        transaction.setCreateDate(new Date());
//        transaction.setTotalPrice(sum(session));
//        ordersService.saveOrders(orders);
//        for (ItemsCart cart : carts) {
//            Product product = cart.getProduct();
//            OrdersDetail orderDetails = new OrdersDetail();
//            orderDetails.setIdorder(orders.getId());
//            orderDetails.setIdproduct(product.getId());
//            orderDetails.setQuantity(cart.getQuantity());
//            orderDetails.setPrice(cart.getQuantity() * product.getPrice());
//            ordersDetailService.saveOrdersDetail(orderDetails);
//        }
//        session.removeAttribute("cart");
//        return new ModelAndView("user/information");
//    }

    private int isExists(Long id, List<Order> carts) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getProduct().getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

}
