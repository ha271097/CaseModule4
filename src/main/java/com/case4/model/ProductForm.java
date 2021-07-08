package com.case4.model;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;

@Data
public class ProductForm {
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private MultipartFile image;
    @ManyToOne
    private Category category;

    public ProductForm() {
    }

    public ProductForm(ProductFormBuilder productFormBuilder) {
        this.id = productFormBuilder.id;
        this.name = productFormBuilder.name;
        this.quantity = productFormBuilder.quantity;
        this.price = productFormBuilder.price;
        this.description = productFormBuilder.description;
        this.category = productFormBuilder.build().category;
        this.image = productFormBuilder.image;
    }

    public static class ProductFormBuilder {
        private Long id;
        private String name;
        private int quantity;
        private double price;
        private String description;
        private MultipartFile image;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public MultipartFile getImage() {
            return image;
        }

        public void setImage(MultipartFile image) {
            this.image = image;
        }

        public ProductFormBuilder(String name) {
            this.name = name;
        }

        public ProductFormBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductFormBuilder image(MultipartFile image) {
            this.image = image;
            return this;
        }

        public ProductForm build() {
            return new ProductForm(this);
        }
    }
}