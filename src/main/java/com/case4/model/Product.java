package com.case4.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private int quantity;
    private double price;
    private String description;
    @Lob

    private String img;


    @ManyToOne
    private Category category;






    public void upQuantity(){
        this.quantity = quantity ++;
    }
    public void downQuantity(){
        this.quantity = quantity --;
    }



    public Product(Long id, String name, int quantity, double price, String description, Category category) {
        this.id = id;
        this.name = name;
        this.quantity =quantity;
        this.price = price;
        this.description = description;
        this.category = category;
    }


    public Product(ProductBuilder productBuilder) {
        this.id = productBuilder.id;
        this.name = productBuilder.name;
        this.quantity = productBuilder.quantity;
        this.price = productBuilder.price;
        this.description = productBuilder.description;
        this.category = productBuilder.build().category;
        this.img = productBuilder.image;
    }



    public static class ProductBuilder {
        private Long id;
        private String name;
        private int quantity;
        private double price;
        private String description;
        private String image;


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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public ProductBuilder(String name) {
            this.name = name;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder image(String image) {
            this.image = image;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
