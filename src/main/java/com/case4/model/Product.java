package com.case4.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
    public void upQuantity(){
        this.quantity = quantity ++;
    }
    public void downQuantity(){
        this.quantity = quantity --;
    }
}
