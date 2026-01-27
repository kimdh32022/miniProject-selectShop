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
     @JoinColumn(name= "productId", nullable = false)
     private Product product;

     @Column
     private Long count;


}
