package com.example.ecommerce.controller;

import com.example.ecommerce.model.Register;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/register")
public class registerController {
    private final UserService userService;
    // Constructor User
    public registerController(UserService userService) {
        this.userService = userService;
    }
    // register User
    @PostMapping
    public ResponseEntity<Api> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isRegistered = userService.register(user);
        if (!isRegistered){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Your registered before ! :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Accepted request :)",200));
    }
    // login user
    @PostMapping("/login")
    public ResponseEntity<Api> login(@RequestBody @Valid Register register,Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isLogin = userService.login(register);
        if (!isLogin){
            return ResponseEntity.status(400).body(new Api("Invalid Username Or Password",400));
        }
        return ResponseEntity.status(200).body(new Api("Welcome Back",200));
    }

}
