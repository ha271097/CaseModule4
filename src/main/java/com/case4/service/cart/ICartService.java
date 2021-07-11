package com.case4.service.cart;

import com.case4.model.Cart;

import java.util.HashMap;

public interface ICartService {

    public HashMap<Long, Cart> addCart(Long id, HashMap<Long, Cart> cartHashMap);
    public HashMap<Long, Cart> editCart(Long id, int quantity, HashMap<Long, Cart> cartHashMap);

    public HashMap<Long, Cart> deleteCart(Long id,  HashMap<Long, Cart> cartHashMap);
    public double totalPrice(HashMap<Long, Cart> cartHashMap);
    public int totalQuantity(HashMap<Long, Cart> cartHashMap);

}
