package com.case4.controller;

import com.case4.model.Transaction;
import com.case4.model.User;
import com.case4.service.product.ProductService;
import com.case4.service.transaction.ITransactionService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    public UserService userService;

    @GetMapping()
    public ModelAndView ListBill(){
        return new ModelAndView("/admin/home", "tran", transactionService.findAll());
    }

    @GetMapping("/listUser")
    public ModelAndView listUser() {
        Iterable<User> users = userService.findAll();
        List<User> userList = new ArrayList<>();
        for (User user : users
        ) {
            if (user.getRole().getRoleName().equals("ROLE_USER")) {
                userList.add(user);
            }
        }
        return new ModelAndView("admin/viewUser", "user",userList);
    }


}
