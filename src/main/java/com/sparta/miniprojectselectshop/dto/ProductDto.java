package com.sparta.miniprojectselectshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private int price;
    private int stock;


    public ProductDto(String productName, int productPrice, int productStock) {
        this.name = productName;
        this.price = productPrice;
        this.stock = productStock;
    }


}
