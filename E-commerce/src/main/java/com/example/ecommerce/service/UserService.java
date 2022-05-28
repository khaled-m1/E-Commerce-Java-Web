package com.example.ecommerce.service;
import com.example.ecommerce.model.Register;
import com.example.ecommerce.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
@AllArgsConstructor
public class UserService {
    private final ProductService productService;
    private final CartService cartService;
    private final CommentService commentService;
    private ArrayList<User> users = new ArrayList<>();
    // View User
    public ArrayList<User> getUsers() {
        return users;
    }
    // Add User
    public boolean addUser(User user) {
        return users.add(user);
    }
    // Edit User
    public boolean editUser(Integer index, User user) {
        users.set(index,user);
        return true;
    }
    // Remove User
    public boolean removeUser(String id) {
        Integer currentUser = getUser(id);
        users.remove((int)currentUser);
        return true;
    }
    // get ID User
    public Integer getUser(String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    // get user for product class
    public User getUserid(String userid) {
        for (User user:users) {
            if (user.getId().equals(userid)){
                return user;
            }
        }
        return null;
    }
    // register
    public boolean register(User user) {
        for (int i = 0; i < users.size(); i++) {
            User currentUser=users.get(i);
            if (currentUser.getUsername().equals(user.getUsername()) ||currentUser.getEmail().equals(user.getEmail())){
                return false;
            }
        }
        users.add(user);
        return true;
    }
    // register login
    public boolean login(Register register) {
        for (int i = 0; i < users.size(); i++) {
            User currentUser=users.get(i);
            if (currentUser.getUsername().equals(register.getUsername())){
                if (currentUser.getPassword().equals(register.getPassword())){
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }
}
