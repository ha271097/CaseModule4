package com.case4.controller;

import com.case4.model.User;
import com.case4.service.category.CategoryService;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView listProduct(){
        return new ModelAndView("/home/index","listProduct",productService.findAll() );
    }

    @GetMapping("/user")
    public ModelAndView homeUser(){
        return new ModelAndView("/home/indexUser","listProduct",productService.findAll() );
    }



}
