package com.example.ecommerce.service;

import com.example.ecommerce.model.Merchant;
import com.example.ecommerce.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    private ArrayList<Merchant> merchants = new ArrayList<>();
    // View Merchant
    public ArrayList<Merchant> getMerchant() {
        return merchants;
    }
    // Add Merchant
    public boolean addMerchant(Merchant merchant) {
        return merchants.add(merchant);
    }
    // Edit Merchant
    public boolean editMerchant(Integer index, Merchant merchant) {
        merchants.set(index,merchant);
        return true;
    }
    // Remove Merchant
    public boolean removeMerchant(String id) {
        Integer currentMerchant = getMerchant(id);
        merchants.remove((int)currentMerchant);
        return true;
    }
    // get ID Merchant
    public Integer getMerchant(String id){
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }
    // get Merchant id for product class
    public Merchant getMerchantid(String merchantid) {
        for (Merchant merchant:merchants) {
            if (merchant.getId().equals(merchantid)){
                return merchant;
            }
        }
        return null;
    }
}
