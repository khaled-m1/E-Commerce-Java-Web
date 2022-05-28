package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    private ArrayList<Cart> carts = new ArrayList<>();

    private final ProductService productService;
    private final UserService userService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    private final PurchaseHistoryService purchaseHistoryService;
    // Constructor User,Product, Merchant,MerchantStock,PurchaseHistory
    public CartService(ProductService productService, UserService userService, MerchantService merchantService, MerchantStockService merchantStockService, PurchaseHistoryService purchaseHistoryService) {
        this.productService = productService;
        this.userService = userService;
        this.merchantService = merchantService;
        this.merchantStockService = merchantStockService;
        this.purchaseHistoryService = purchaseHistoryService;
    }
    // View all carts
    public ArrayList<Cart> getCart() {
        return carts;
    }
    // add product to card
    public boolean addProdectToCard(Product product) {
        ArrayList<Product> productList = productService.getProduct();
        return productList.add(product);
    }
    // remove product from a card
    public boolean removeProdectFromCard(String id){
        ArrayList<Product> productList = productService.getProduct();
        Integer currentProductList = getProduct(id);
        productList.remove((int)currentProductList);
        return true;
    }
    // helper method to get Product id
    public Integer getProduct(String id){
        ArrayList<Product> productList = productService.getProduct();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    // user can buy a product with a cart
    public Integer cartPurchase(String userid, String productid, String merchantid, String merchantStockid, String purchaseHistoryid,String cardid) {
        Cart cart =getCardid(cardid);
        Product product=productService.getProductid(productid);
        User user=userService.getUserid(userid);
        Merchant merchant = merchantService.getMerchantid(merchantid);
        MerchantStock merchantStock = merchantStockService.getMerchantStockid(merchantStockid);
        PurchaseHistory purchaseHistory = purchaseHistoryService.getPurchaseHistory(purchaseHistoryid);
        ArrayList<Product> productList = productService.getProduct();
        if (product == null || user == null || merchant == null || carts == null){
            return  -1;
        }
        if (user.getBalance()<product.getPrice()){
            return 0;
        }
        if (merchantStock.getStock()==null){
            return 1;
        }

        Integer oldProduct= Integer.valueOf(merchantStock.getProductid());
        merchantStock.setStock(String.valueOf(oldProduct-1));

        Integer oldbalance = user.getBalance();
        user.setBalance(oldbalance-product.getPrice());
        purchaseHistory.addNewPurchaseHistory(purchaseHistory);
        return 2;
    }
    public Cart getCardid(String cardid){
        for (Cart cart:carts) {
            if (cart.getId().equals(cardid)){
                return cart;
            }
        }
        return null;
    }
}
