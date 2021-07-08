package com.case4.controller;

//import com.case4.service.user.UserService;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/home")
@RestController
public class AuthorController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ModelAndView home(){
        return new ModelAndView("/shop/dailyShop/index", "list", productService.findAll()) ;
    }

//    @GetMapping("/cart")
//    public ModelAndView cart(){
//        return new ModelAndView("/cart", "list", userService.findAll()) ;
//    }

}
