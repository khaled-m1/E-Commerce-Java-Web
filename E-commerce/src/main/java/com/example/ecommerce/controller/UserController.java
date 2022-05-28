package com.example.ecommerce.controller;
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
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final ProductService productService;
    // Constructor User, Product
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }
    // View User
    @GetMapping
    public ResponseEntity<ArrayList<User>> getUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    // Add User
    @PostMapping
    public ResponseEntity<Api> addUser(@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isAcceptedAdd = userService.addUser(user);
        if (!isAcceptedAdd){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Accepted :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Accepted request :)",200));
    }
    // Edit User
    @PutMapping("/{index}")
    public ResponseEntity<Api> editUser(@PathVariable Integer index, @RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }
        boolean isUserEdit = userService.editUser(index,user);
        if (!isUserEdit){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Edited :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Edited request :)",200));
    }
    // Remove User
    @DeleteMapping("/remove")
    public ResponseEntity<Api> removeUser(@RequestParam String id){
        boolean isRemovedUser=userService.removeUser(id);
        if (!isRemovedUser){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Sorry but your request not Removed :(",400));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Api("Removed request :)",200));
    }
}
