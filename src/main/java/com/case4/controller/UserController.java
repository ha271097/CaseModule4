package com.case4.controller;


import com.case4.dto.SignInForm;
import com.case4.model.Product;
import com.case4.model.User;
import com.case4.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Iterable<User>> findAll(){

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findByID(@PathVariable Long id){

        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody SignInForm signInForm){
        String username1 = signInForm.getUsername();
        String password1 = signInForm.getPassword();
        Iterable<User> userIterable = userService.findAll();
        for (User user: userIterable
             ) {
            if (user.getUsername().equals(username1) && user.getPassword().equals(password1)){
                return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //them vao gio hang(order)
//    @PostMapping("/add/{id}")
//    public ResponseEntity<?> addToCart(){
//
//    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateUser(@PathVariable Long id, @RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
