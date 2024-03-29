package com.case4.controller;

//import com.case4.service.user.UserService;

import com.case4.dto.SignInForm;
import com.case4.model.Role;
import com.case4.service.product.ProductService;
import com.case4.service.role.RoleService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/login")
@RestController
public class
AuthorController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public Iterable<Role> categories(){
        return roleService.findAll();
    }

    @GetMapping("")
    public ModelAndView loginForm(){
        ModelAndView mav = new ModelAndView("admin/login");
        mav.addObject("sign", new SignInForm());
        return mav;
    }


}
