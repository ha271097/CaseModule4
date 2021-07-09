package com.case4.controller;

//import com.case4.service.user.UserService;
import com.case4.dto.SignInForm;
import com.case4.model.Role;
import com.case4.model.User;
import com.case4.service.product.ProductService;
import com.case4.service.role.RoleService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("login")
@RestController
public class AuthorController {

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

    @GetMapping("/user")
    public ModelAndView loginForm(){
        ModelAndView mav = new ModelAndView("admin/login");
        mav.addObject("sign", new SignInForm());
        return mav;
    }

    @PostMapping("/user")
    public ModelAndView login(SignInForm signInForm){
        ModelAndView modelAndView = new ModelAndView("admin/login");
        String username = signInForm.getUsername();
        String password = signInForm.getPassword();
        Iterable<User> users = userService.findAll();

        for (User user: users
        ) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                if(user.getRole().getRoleName().toString().equals("ROLE_USER")){
                    ModelAndView mav = new ModelAndView("home/index");
                    mav.addObject("user", user);
                    return mav;
                }
                if(user.getRole().getRoleName().toString().equals("ROLE_ADMIN")){
                    ModelAndView mav = new ModelAndView("admin/home");
                    mav.addObject("user", user);
                    return mav;
                }
            }
        }
        String mes = "Tài khoản hoặc mật khẩu không đúng!";
       modelAndView.addObject("mes", mes);
       modelAndView.addObject("sign", new SignInForm());
       return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registation(){
        return new ModelAndView("/home/registration","user", new User());
    }





}
