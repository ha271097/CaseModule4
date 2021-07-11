package com.case4.controller;

//import com.case4.service.user.UserService;
import com.case4.dto.CreateUser;
import com.case4.dto.SignInForm;
import com.case4.model.Role;
import com.case4.model.User;
import com.case4.service.product.ProductService;
import com.case4.service.role.RoleService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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


    @ModelAttribute("customer")
    public User getPrincipal() {
        User userInfo = new User();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userInfo = userService.getUserByName(((UserDetails) principal).getUsername());
        }
        return userInfo;
    }

//    @PostMapping("")
//    public ModelAndView login(SignInForm signInForm){
//        ModelAndView modelAndView = new ModelAndView("admin/login");
//        String username = signInForm.getUsername();
//        String password = signInForm.getPassword();
//        Iterable<User> users = userService.findAll();
//
//        for (User user: users
//        ) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
//                if(user.getRole().getRoleName().equals("ROLE_USER")){
//                    ModelAndView mav = new ModelAndView("home/index");
//                    mav.addObject("user", user);
//                    return mav;
//                }
//                if(user.getRole().getRoleName().equals("ROLE_ADMIN")){
//                    ModelAndView mav = new ModelAndView("admin/helloadmin");
//                    mav.addObject("user", user);
//                    return mav;
//                }
//            }
//        }
//        String mes = "Tài khoản hoặc mật khẩu không đúng!";
//       modelAndView.addObject("mes", mes);
//       modelAndView.addObject("sign", new SignInForm());
//       return modelAndView;
//    }

//    @GetMapping("/create")
//    public ModelAndView registation(){
//        return new ModelAndView("/home/registration","user", new CreateUser());
//    }
//
//    @PostMapping("/create")
//    public ModelAndView saveUser(@Valid CreateUser createUser){
//        User user = new User();
//        user.setUsername(createUser.getUsername());
//        user.setEmail(createUser.getEmail());
//        user.setName(createUser.getName());
//        user.setPassword(createUser.getPassword());
//        user.setRole(roleService.findByName("ROLE_USER"));
//        userService.save(user);
//        ModelAndView mav = new ModelAndView("admin/login");
//        mav.addObject("sign", new SignInForm());
//        return mav;
//    }
}
