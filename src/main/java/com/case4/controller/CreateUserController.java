package com.case4.controller;

import com.case4.dto.CreateUser;
import com.case4.dto.SignInForm;
import com.case4.model.User;
import com.case4.service.role.IRoleService;
import com.case4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
@Controller
@RequestMapping("/create")
public class CreateUserController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ModelAndView registation() {
        return new ModelAndView("/home/registration", "user", new CreateUser());
    }

    @PostMapping("")
    public ModelAndView saveUser(@Valid CreateUser createUser, BindingResult bindingResult) {
//        if (!bindingResult.hasFieldErrors()) {
            User user = new User();
            user.setUsername(createUser.getUsername());
            user.setEmail(createUser.getEmail());
            user.setName(createUser.getName());
            user.setPassword(createUser.getPassword());
            user.setRole(roleService.findByName("ROLE_USER"));
            userService.save(user);
//        ModelAndView mav = newModelAndView("admin/login");
//        mav.addObject("sign", new SignInForm());
            return new ModelAndView("redirect:/login");
//        }
//        return new ModelAndView("/home/registration","user", new CreateUser());

    }
}
