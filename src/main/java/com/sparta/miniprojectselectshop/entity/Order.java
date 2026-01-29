package com.sparta.miniprojectselectshop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {

     @Id
     @GeneratedValue( strategy = GenerationType.IDENTITY)
     private Long orderId;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name= "product_Id", nullable = false)
     private Product product;



     protected Order(Product product) {
          this.product = product;
     }

     public static Order create(Product product){
          return   Order.builder()
                  .product(product)
                  .build();
     }
}
