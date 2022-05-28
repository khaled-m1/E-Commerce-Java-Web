package com.example.ecommerce.controller;

import com.example.ecommerce.model.Comment;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CommentService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/comment")//Comment
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private ProductService productService;
    // Constructor User, Product, Comment
    public CommentController(CommentService commentService, UserService userService, ProductService productService) {
        this.commentService = commentService;
        this.userService = userService;
        this.productService = productService;
    }
    // View Comment
    @GetMapping
    public ResponseEntity<ArrayList<Comment>> getComment(){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment());
    }
    @PostMapping
    public ResponseEntity<Api> addComment(@RequestBody Comment message){
      boolean isAddComment = commentService.addComment(message);
      if (!isAddComment){
          return ResponseEntity.status(400).body(new Api("Your comment not add :(",400));
      }
      return ResponseEntity.status(201).body(new Api("yor Comment added :)",201));
    }
    // get all the comments for a product
    @GetMapping("/comment-product")
    public ResponseEntity getCommentProduct(@RequestBody Comment comment, @RequestBody Product product){
        boolean isAddedCommentProduct = commentService.getCommentProduct(comment,product);
        if (!isAddedCommentProduct){
            return ResponseEntity.status(400).body("Yor comment not added :(");
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentProduct(comment,product));
    }
    // get all the rate 5 products
    @GetMapping("/comment-product-rate")
    public ResponseEntity getCommentProductRate(@RequestBody Comment comment, @RequestBody Product product){
        boolean isRateCommentProduct = commentService.getCommentProductRate(comment,product);
        if (!isRateCommentProduct){
            return ResponseEntity.status(400).body("Yor comment not added :(");
        }
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentProductRate(comment,product));
    }
}
