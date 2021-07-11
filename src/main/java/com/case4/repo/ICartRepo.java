package com.case4.repo;

import com.case4.model.Cart;
import com.case4.model.Product;
import com.case4.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ICartRepo implements JpaRepository<Cart, Long> {

    @Autowired
    private ProductService productService;

    public HashMap<Long, Cart> addCart(Long id, HashMap<Long, Cart> cartHashMap){
        Cart itemCart = new Cart();
        Optional<Product> product = productService.findById(id);
        if (product != null && cartHashMap.containsKey(id)){
           itemCart = cartHashMap.get(id);
            itemCart.setQuantity(itemCart.getQuantity() +1);
            itemCart.setTotalPrice(itemCart.getQuantity()*itemCart.getProduct().getPrice());
        }

        else {
            itemCart.setProduct(product.get());
            itemCart.setQuantity(1);
            itemCart.setTotalPrice(product.get().getPrice());
        }
        cartHashMap.put(id, itemCart);
        return cartHashMap;
    }

    public HashMap<Long, Cart> editCart(Long id, int quantity, HashMap<Long, Cart> cartHashMap){

        if (cartHashMap == null){
            return cartHashMap;
        }
        Cart itemCart = new Cart();
        if (cartHashMap.containsKey(id)){
            itemCart = cartHashMap.get(id);
            itemCart.setQuantity(quantity);
            double totalPrice = quantity*itemCart.getProduct().getPrice();
            itemCart.setTotalPrice(totalPrice);
        }
        cartHashMap.put(id, itemCart);
        return  cartHashMap;

    }

    public HashMap<Long, Cart> deleteCart(Long id,  HashMap<Long, Cart> cartHashMap){
            if (cartHashMap == null){
                return cartHashMap;
            }

            if (cartHashMap.containsKey(id)){
                cartHashMap.remove(id);
            }
        return  cartHashMap;

    }

    public int totalQuantity(HashMap<Long, Cart> cartHashMap){
        int totalQuantity = 0;
        for (Map.Entry<Long, Cart> itemCart: cartHashMap.entrySet()
             ) {
            totalQuantity += itemCart.getValue().getQuantity();
        }
        return  totalQuantity;

    }

    public double totalPrice(HashMap<Long, Cart> cartHashMap){
        double totalPrice = 0;
        for (Map.Entry<Long, Cart> itemCart: cartHashMap.entrySet()
        ) {
            totalPrice += itemCart.getValue().getTotalPrice();
        }
        return  totalPrice;
    }


    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public List<Cart> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Cart> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Cart entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Cart> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Cart> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Cart> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Cart> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Cart> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Cart> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Cart> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Cart getOne(Long aLong) {
        return null;
    }

    @Override
    public Cart getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Cart> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Cart> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Cart> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Cart> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Cart> boolean exists(Example<S> example) {
        return false;
    }
}
