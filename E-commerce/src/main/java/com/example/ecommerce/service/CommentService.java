package com.example.ecommerce.service;

import com.example.ecommerce.model.Comment;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {
    private ArrayList<Comment> comments = new ArrayList<>();
    // View Comment
    public ArrayList<Comment> getComment() {
        return comments;
    }
    // get ID Comment
    public Comment getCommentid(String comment){
        for (Comment comment1:comments) {
            if (comment1.getId().equals(comment)){
                return comment1;
            }
        }
        return null;
    }
    // Add comment
    public boolean addComment(Comment message) {
        return comments.add(message);
    }
    // get comment product
    public boolean getCommentProduct(Comment comment, Product product) {
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).equals(product.getCommentsList())){
                return true;
            }
        }
        return true;
    }
    // get all the rate 5 products
    public boolean getCommentProductRate(Comment comment, Product product) {
        for (int i = 0; i < comments.size(); i++) {
            if (product.getCommentsList().equals(comment.getRate().equals(5))){
                return true;
            }
        }
        return true;
    }
}
