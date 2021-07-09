package com.case4.controller;

import com.case4.service.product.ProductService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    public UserService userService;

    @GetMapping()
    public ModelAndView ListProduct(){
        return new ModelAndView("/admin/home", "products", productService.findAll());
    }

    @GetMapping("/listUser")
    public ModelAndView listUser(){
        return new ModelAndView("/admin/viewUser","list",userService.findAll());
    }

}
