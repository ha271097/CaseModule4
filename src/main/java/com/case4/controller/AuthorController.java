package com.case4.controller;

import com.case4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/home")
@RestController
@CrossOrigin("*")
public class AuthorController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public ResponseEntity home(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/cart")
//    public ModelAndView cart(){
//        return new ModelAndView("/cart", "list", userService.findAll()) ;
//    }

}
