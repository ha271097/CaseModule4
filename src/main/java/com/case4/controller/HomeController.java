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
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView listProduct(){
        return new ModelAndView("/home/index","listProduct",productService.findAll() );
    }

//    @GetMapping("/registration")
//    public ModelAndView registation(){
//        return new ModelAndView("/home/registration","user", new User());
//    }




//    @GetMapping("/category/{category}")
//    public ModelAndView listProductByCategory(@PathVariable("category") String category){
//        ModelAndView modelAndView = new ModelAndView("home/index");
//
//        if(category.equals("dm1")){
//            products = productService.findAllByTypeProduct(categoryService.getTypeProduct(1L));
//        } else if (type.equals("dm2")){
//            products = productService.findAllByTypeProduct(typeProductService.getTypeProduct(2L), pageable);
//        } else {
//            products = productService.findAllByTypeProduct(typeProductService.getTypeProduct(3L), pageable);
//        }
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }

}
