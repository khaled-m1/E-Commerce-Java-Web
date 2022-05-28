package com.example.ecommerce.service;

import com.example.ecommerce.model.MerchantStock;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MerchantStockService {
    private ArrayList<MerchantStock> merchantStocks = new ArrayList<>();
    private final ProductService productService;
    // View Merchant Stock
    public ArrayList<MerchantStock> getMerchantStock() {
        return merchantStocks;
    }
    // Add Merchant Stock
    public boolean addMerchantStock(MerchantStock merchantStock) {
        return merchantStocks.add(merchantStock);
    }
    // Edit Merchant Stock
    public boolean editMerchantStock(Integer index, MerchantStock merchantStock) {
        merchantStocks.set(index,merchantStock);
        return true;
    }
    // Remove Merchant Stock
    public boolean removeMerchantStock(String id) {
        Integer currentMerchantStock = getMerchantStock(id);
        merchantStocks.remove((int)currentMerchantStock);
        return true;
    }
    // get ID Merchant Stock
    public Integer getMerchantStock(String id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    // add product to a merchantStock
    public boolean addProdectTomerchantStock(Product product) {
        ArrayList<Product> productList = productService.getProduct();
        return productList.add(product);
    }
    // get ID Merchant Stock to product and card
    public MerchantStock getMerchantStockid(String merchantStockid) {
        for (MerchantStock merchantStock:merchantStocks) {
            if (merchantStock.getId().equals(merchantStockid)){
                return merchantStock;
            }
        }
        return null;
    }
}
