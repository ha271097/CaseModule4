package com.case4.controller;

import com.case4.service.admin.AdminService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/home")
@RestController
public class AuthorController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @GetMapping()
    public ModelAndView home(){
        return new ModelAndView("/home", "list", adminService.findAll()) ;
    }




}
