package com.sparta.miniprojectselectshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor

@Table(name = "orders")
public class Order {

     @Id
     @GeneratedValue( strategy = GenerationType.IDENTITY)
     private Long orderId;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name= "product_Id", nullable = false)
     private Product product;


     public Order(Product product) {
          this.product = product;
     }
}
