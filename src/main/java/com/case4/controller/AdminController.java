package com.case4.controller;

import com.case4.model.Category;
import com.case4.model.Product;
import com.case4.model.Transaction;
import com.case4.model.User;
import com.case4.service.category.ICategoryService;
import com.case4.service.product.IProductService;
import com.case4.service.product.ProductService;
import com.case4.service.transaction.ITransactionService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ITransactionService transactionService;


    @Autowired
    private UserService userService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categorys")
    private Iterable<Category> categoryList(){
       return categoryService.findAll();
    }

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

    @GetMapping("/product")
    public ModelAndView createProduct(){
        return new ModelAndView("admin/product", "products", productService.findAll());
    }

    @GetMapping("/create")
    public ModelAndView formCreate(){
        return new ModelAndView("admin/create","product", new Product());
    }

    @PostMapping("/create")
    public String saveproduct(Product product){
        productService.save(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/edit")
    public ModelAndView updateForm(Long id){
        Product product = productService.findById(id).get();
        return new ModelAndView("admin/edit", "product", product);
    }

    @PostMapping("/edit")
    public String editProduct(Product product){
        productService.save(product);
        return "redirect:/admin/product";
    }


    @GetMapping("/delete")
    public String deleteProduct(Long id){
        productService.remove(id);
        return "redirect:/admin/product";
    }


}
