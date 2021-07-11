package com.case4.service.cart;

import com.case4.model.Cart;
import com.case4.repo.ICartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CartService implements ICartService{

    @Autowired
    private ICartRepo cartRepo;

    @Override
    public HashMap<Long, Cart> addCart(Long id, HashMap<Long, Cart> cartHashMap) {
        return cartRepo.addCart(id, cartHashMap);
    }

    @Override
    public HashMap<Long, Cart> editCart(Long id, int quantity, HashMap<Long, Cart> cartHashMap) {
        return cartRepo.editCart(id,quantity,cartHashMap);
    }

    @Override
    public HashMap<Long, Cart> deleteCart(Long id, HashMap<Long, Cart> cartHashMap) {
        return cartRepo.deleteCart(id,cartHashMap);
    }

    @Override
    public double totalPrice(HashMap<Long, Cart> cartHashMap) {
        return cartRepo.totalQuantity(cartHashMap);
    }

    @Override
    public int totalQuantity(HashMap<Long, Cart> cartHashMap) {
        return (int) cartRepo.totalPrice(cartHashMap);
    }
}
