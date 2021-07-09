//package com.case4.controller;
//
//import com.case4.model.Order;
//import com.case4.model.Product;
//import com.case4.service.product.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/orders")
//public class OrderController {
//
//    @Autowired
//    private ProductService productService;
//
//    @@GetMapping(value = "add/{productId}.html")
//    public String viewAdd(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
//        HashMap<Long, OrderController> orderList = (HashMap<Long, OrderController>) session.getAttribute("myCartItems");
//        if (orderList == null) {
//            orderList = new HashMap<>();
//        }
//        Optional<Product> product = productService.findById(productId);
//        if (product != null) {
//            if (orderList.containsKey(productId)) {
//                Order item = orderList.get(productId);
//                item.setProduct(product);
//                item.setQuantity(item.getQuantity() + 1);
//                orderList.put(productId, item);
//            } else {
//                Order item = new OrderController();
//                item.setProduct(product);
//                item.setQuantity(1);
//                orderList.put(productId, item);
//            }
//        }
//        session.setAttribute("myCartItems", orderList);
//        session.setAttribute("myCartTotal", totalPrice(orderList));
//        session.setAttribute("myCartNum", orderList.size());
//        return "orders/order";
//    }
//
//    @RequestMapping(value = "sub/{productId}.html", method = RequestMethod.GET)
//    public String viewUpdate(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
//        HashMap<Long, OrderController> orderItem = (HashMap<Long, OrderController>) session.getAttribute("myCartItems");
//        if (orderItem == null) {
//            orderItem = new HashMap<>();
//        }
//        session.setAttribute("myCartItems", orderItem);
//        return "orders/order";
//    }
//
//    @RequestMapping(value = "remove/{productId}.html", method = RequestMethod.GET)
//    public String viewRemove(ModelMap mm, HttpSession session, @PathVariable("productId") long productId) {
//        HashMap<Long, OrderController> orderItem = (HashMap<Long, OrderController>) session.getAttribute("myCartItems");
//        if (orderItem == null) {
//            orderItem = new HashMap<>();
//        }
//        if (orderItem.containsKey(productId)) {
//            orderItem.remove(productId);
//        }
//        session.setAttribute("myCartItems", orderItem);
//        session.setAttribute("myCartTotal", totalPrice(orderItem));
//        session.setAttribute("myCartNum", orderItem.size());
//        return "orders/order";
//    }
//
//    public double totalPrice(HashMap<Long, Order> cartItems) {
//        int count = 0;
//        for (Map.Entry<Long, Order> list : cartItems.entrySet()) {
//            count += list.getValue().getProduct().getQuantity() * list.getValue().getQuantity();
//        }
//        return count;
//    }
//
//}
