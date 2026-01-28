package com.sparta.miniprojectselectshop.entity;

import com.sparta.miniprojectselectshop.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int productPrice;

    @Column(nullable = false)
    private int productStock;

    @Builder
    public Product(String productName, int productPrice, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public static Product create(String name, int price, int stock) {
        if(name == null || price <= 0 || stock <= 0){
            throw new IllegalArgumentException("물건의 이름을 꼭 입력하거나 가격과 수량이 0보다 커야합니다.");
        }
        return Product.builder()
                .productName(name)
                .productPrice(price)
                .productStock(stock)
                .build();
    }

    public void update(String productName, int productPrice, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public void decreaseStock() {
        if (this.productStock <= 0) {
            throw new IllegalArgumentException("재고가 없습니다.");
        }
        this.productStock -= 1;
    }
}
