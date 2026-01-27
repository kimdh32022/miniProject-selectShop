package com.sparta.miniprojectselectshop.entity;

import jakarta.persistence.*;

@Entity
public class Order {

     @Id
     @GeneratedValue( strategy = GenerationType.IDENTITY)
     private int orderId;

     @ManyToOne
     @JoinColumn(name= "productId", nullable = false)
     private Product product;

}
