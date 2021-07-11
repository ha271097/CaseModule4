package com.case4.model;

import javax.persistence.*;


public class Cart {

    private Long id;

    public Cart() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private int quantity;
    private double totalPrice;


    private User user;


    private Product product;


    public Cart(int quantity, double totalPrice, User user, Product product) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.user = user;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
