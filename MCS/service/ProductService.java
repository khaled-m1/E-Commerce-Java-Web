package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private ArrayList<Product> products = new ArrayList<>();
    private final CartService cartService;
    private final UserService userService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    private final PurchaseHistoryService purchaseHistoryService;
    // Constructor User,Product, Merchant,MerchantStock,PurchaseHistory,Cart
    public ProductService(CartService cartService, UserService userService, MerchantService merchantService, MerchantStockService merchantStockService, PurchaseHistoryService purchaseHistoryService) {
        this.cartService = cartService;
        this.userService = userService;
        this.merchantService = merchantService;
        this.merchantStockService = merchantStockService;
        this.purchaseHistoryService = purchaseHistoryService;
    }
    // View Product
    public ArrayList<Product> getProduct() {
        return products;
    }
    // Add Product
    public boolean addProduct(Product product) {
        return products.add(product);
    }
    // View Product
    public boolean editProduct(Integer index, Product product) {
        products.set(index,product);
        return true;
    }
    // Remove Product
    public boolean removeProduct(String id) {
        Integer currentProduct = getProduct(id);
        products.remove((int)currentProduct);
        return true;
    }
    // get ID Product
    public Integer getProduct(String id){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    // user can buy a product directly without a cart
    public Integer directlyPurchase(String userid, String productid, String merchantid,String merchantStockid,String purchaseHistoryid) {
        Product product=getProductid(productid);
        User user=userService.getUserid(userid);
        Merchant merchant = merchantService.getMerchantid(merchantid);
        MerchantStock merchantStock = merchantStockService.getMerchantStockid(merchantStockid);
        PurchaseHistory purchaseHistory = purchaseHistoryService.getPurchaseHistory(purchaseHistoryid);
        // check if null
        if (product == null || user == null || merchant == null){
            return  -1;
        }
        // check if user balance != product price
        if (user.getBalance()<product.getPrice()){
            return 0;
        }
        // check if merchantStock null
        if (merchantStock.getStock()==null){
            return 1;
        }
        // Change value of product
        Integer oldProduct= Integer.valueOf(merchantStock.getProductid());
        merchantStock.setStock(String.valueOf(oldProduct-1));
        // Change value of balance
        Integer oldbalance = user.getBalance();
        user.setBalance(oldbalance-product.getPrice());
        // Add New Purchase History
        purchaseHistory.addNewPurchaseHistory(purchaseHistory);
        return 2;
    }
    // get Product ID for Card and product
    public Product getProductid(String productid){
       for (Product product:products) {
           if (product.getId().equals(productid)){
               return product;
           }
       }
       return null;
   }




}
