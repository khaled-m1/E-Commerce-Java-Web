package com.example.ecommerce.model;
import javax.validation.constraints.*;
import java.util.ArrayList;


public class PurchaseHistory extends User {
    @NotEmpty(message = "ID must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your id must be have to be 3 character long")
    private String id;

    @NotEmpty(message = "userid must not be empty")
    @Size(min = 5,message = "Your userid must be  have to be 5 length long")
    private String userid;

    @NotEmpty(message = "productid must not be empty")
    @Digits(integer = 3,fraction = 0,message = "Your productid must be have to be 3 character long")
    private String productid;

    @NotNull(message = "price must not be empty")
    @Positive(message = "price have to be positive")
    private Integer price;

    private ArrayList<User> purchaseuser;

     public PurchaseHistory(@NotEmpty(message = "ID must not be empty")
                            @Digits(integer = 3, fraction = 0, message = "Your id must be have to be 3 character long") String id,
                            @NotEmpty(message = "Username must not be empty")
                            @Size(min = 5, message = "Your username must be  have to be 5 length long") String username,
                            @NotEmpty(message = "Password must not be empty")
                            @Size(min = 6, message = "Your password must be  have to be 6 length long")
                            @Digits(integer = 6, fraction = 0, message = "password must be An Integer numbers") String password,
                            @NotEmpty(message = "Email must not be empty")
                            @Email(message = "must be valid email") String email,
                            @NotEmpty(message = "role must not be empty")
                            @Pattern(regexp = "(Admin|Customer)", message = "have to be in ( “Admin”,”Customer”)") String role,
                            @NotNull(message = "balance must not be empty")
                            @Positive(message = "balance have to be positive")
                            @Digits(integer = 10, fraction = 5) Integer balance, String id1, String userid, String productid, Integer price, ArrayList<User> purchaseuser) {
         super(id, username, password, email, role, balance);
         this.id = id1;
         this.userid = userid;
         this.productid = productid;
         this.price = price;
         this.purchaseuser = purchaseuser;
     }

     public boolean addNewPurchaseHistory(PurchaseHistory purchaseHistory) {
        return purchaseuser.add(purchaseHistory);
     }

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getUserid() {
         return userid;
     }

     public void setUserid(String userid) {
         this.userid = userid;
     }

     public String getProductid() {
         return productid;
     }

     public void setProductid(String productid) {
         this.productid = productid;
     }

     public Integer getPrice() {
         return price;
     }

     public void setPrice(Integer price) {
         this.price = price;
     }

     public ArrayList<User> getPurchaseuser() {
         return purchaseuser;
     }

     public void setPurchaseuser(ArrayList<User> purchaseuser) {
         this.purchaseuser = purchaseuser;
     }
 }
