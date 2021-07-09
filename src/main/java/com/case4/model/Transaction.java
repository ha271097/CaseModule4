package com.case4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Status status;
    @ManyToOne
    private User user;
    @NotNull
    private String address;
    private String message;
    private Date createDate;
    private Date completeDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "transaction_order",
    joinColumns = @JoinColumn(name = "transaction_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    List<Order> orders = new ArrayList<>();


//    public void setUser(String name) {
//        this.user = user;
//    }
}
