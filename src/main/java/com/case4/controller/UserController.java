package com.case4.controller;
//
//
//import com.case4.dto.SignInForm;
//import com.case4.model.Category;
//import com.case4.model.Product;
//import com.case4.model.Role;
//import com.case4.model.User;
//import com.case4.service.role.RoleService;
//import com.case4.service.user.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Optional;
//@Controller
//@RestController
////@RequestMapping("")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RoleService roleService;
//
//    @ModelAttribute("roles")
//    public Iterable<Role> categories(){
//        return roleService.findAll();
//    }


//    @GetMapping("")
//    public ModelAndView listUser(){
//        return new ModelAndView("/admin/home","list",userService.findAll());
//    }




//    @GetMapping("/create")
//    public ModelAndView showFormCreate(){
//        return new ModelAndView("/users/create","user",new User());
//    }
//
//    @PostMapping("/create")
//    public ModelAndView createUser(User user){
//        userService.save(user);
//        return new ModelAndView("redirect:/users");
//    }




//
//    @GetMapping("/{id}/edit")
//    public ModelAndView showFormEdit(@PathVariable Long id){
//        ModelAndView modelAndView = new ModelAndView("/users/edit");
//        modelAndView.addObject("user", userService.findById(id));
//        return modelAndView;
//    }
//
//    @PostMapping("/edit")
//    public ModelAndView edit(User user){
//        userService.save(user);
//        return new ModelAndView("redirect:/products");
//    }
//
//
//
//    @GetMapping("/{id}/delete")
//    public ModelAndView showFormDelete(@PathVariable Long id){
//        return new ModelAndView("/users/delete","user", userService.findById(id));
//    }
//
//
//    @PostMapping("/delete")
//    public ModelAndView delete(Product product){
//        userService.remove(product.getId());
//        return new ModelAndView("redirect:/users");
//    }








//    @GetMapping()
//    public ResponseEntity<Iterable<User>> findAll(){
//
//        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<User>> findByID(@PathVariable Long id){
//
//        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Product> saveUser(@RequestBody User user) {
//        userService.save(user);
//        return new ResponseEntity<>( HttpStatus.CREATED);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(SignInForm signInForm){
//        String username = signInForm.getUsername();
//        String password = signInForm.getPassword();
//        Iterable<User> userIterable = userService.findAll();
//        for (User user: userIterable
//             ) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)){
//                return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
//            }
//        }
//        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateUser(@PathVariable Long id, @RequestBody User user){
//        userService.save(user);
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable Long id){
//        userService.remove(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


//}
