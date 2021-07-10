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

@RequestMapping("/home")
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

    @GetMapping()
    public ModelAndView home(){
        return new ModelAndView("/home/index", "list", productService.findAll()) ;
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
                if(user.getRole().getRoleName().equals("ROLE_USER")){
                    ModelAndView mav = new ModelAndView("home/helloUser");
                    mav.addObject("user", user);
                    return mav;
                }
                if(user.getRole().getRoleName().equals("ROLE_ADMIN")){
                    ModelAndView mav = new ModelAndView("admin/helloadmin");
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

    @GetMapping("/create")
    public ModelAndView registation(){
        return new ModelAndView("/home/registration","user", new User());
    }





}
