package com.case4.controller;

import com.case4.model.Category;
import com.case4.model.Product;
import com.case4.model.ProductForm;
import com.case4.service.category.CategoryService;
import com.case4.service.product.ProductService;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin

public class ProductController {

    @Value("${upload.path}")
    private String fileUpload;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findByID(@PathVariable Long id){


    @GetMapping("")
    public ModelAndView listProduct(){
        ModelAndView modelAndView = new ModelAndView("/products/index");
        List<Product> products = (List<Product>) productService.findAll();
        modelAndView.addObject("list", products);
        modelAndView.addObject("message", "Thanh cong");
        return modelAndView;
    }



    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("/products/create");
        modelAndView.addObject("product", new ProductForm());
        return modelAndView;
    }



    @PostMapping("/create")
    public RedirectView createProduct(@ModelAttribute ProductForm product){
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
        return new RedirectView("/products");
    }


//    @GetMapping("/{id}/edit")
//    @PostMapping("/edit/{id}")
//    public ModelAndView updateProduct(@ModelAttribute("product") Product product,
//                                      @SortDefault(sort = {"id"}) @PageableDefault(value = 15) Pageable pageable) {
//        Optional<Product> product1 = productService.findById(product.getId());
//        MultipartFile file = product.getImg();
//        String image = file.getOriginalFilename();
//        String fileUpload = environment.getProperty("upload.path");
//        try {
//            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (!Objects.equals(image, "")) {
//            product.setAvatar(image);
//        } else {
//            product.setAvatar(product1.getAvatar());
//        }
//        productService.saveProduct(product);
//        Page<Product> products = productService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("admin/home");
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }
    @PostMapping("/edit")
    public ModelAndView edit(Product product){
        productService.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showFormDelete(@PathVariable Long id){
        return new ModelAndView("/products/delete","product", productService.findById(id));
    }


    @PostMapping("/delete")
    public ModelAndView delete(Product product){
        productService.remove(product.getId());
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/{id}/addToCart")
    public ModelAndView addToCart(@PathVariable Long id){
        return new ModelAndView("/");
    }


}
