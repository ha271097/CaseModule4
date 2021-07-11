package com.case4.controller;

import com.case4.model.*;
import com.case4.service.bill.BillService;
import com.case4.service.billdetails.BillDetailsService;
import com.case4.service.category.ICategoryService;
import com.case4.service.product.IProductService;
import com.case4.service.product.ProductService;
import com.case4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private BillService billService;

    @Autowired
    private BillDetailsService billDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Value("${upload.path}")
    private String fileUpload;

    @ModelAttribute("categorys")
    private Iterable<Category> categoryList(){
       return categoryService.findAll();
    }

    @GetMapping()
    public ModelAndView ListBill(){
        return new ModelAndView("/admin/home", "billdetails", billDetailsService.findAll());
    }

//    @GetMapping("/billuser/{id}")
//    public ModelAndView listBillByUser(@PathVariable Long id){
//        Optional<User> user = userService.findById(id);
//        Bill bill = billService.
//        return new ModelAndView("/admin/billuser","billdetails", billDetailsService.);
//
//    }


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
        return new ModelAndView("admin/create","product", new ProductForm());
    }

//    @PostMapping("/create")
//    public String saveproduct(Product product){
//        productService.save(product);
//        return "redirect:/admin/product";
//    }


    @PostMapping("/create")
    public RedirectView createProduct(@ModelAttribute ProductForm product) {
        Product product1 = new Product(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory()
        );
        MultipartFile multipartFile = product.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(product.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product1.setImg(fileName);
        productService.save(product1);
        return new RedirectView("/admin/product");
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
