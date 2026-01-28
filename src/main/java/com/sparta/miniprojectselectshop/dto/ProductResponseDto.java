package com.sparta.miniprojectselectshop.dto;

import com.sparta.miniprojectselectshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String productName;
    private int productPrice;
    private int productStock;


    public ProductResponseDto(String productName, int productPrice, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public static ProductResponseDto from(Product product){

        return new ProductResponseDto(
                product.getProductName(),
                product.getProductPrice(),
                product.getProductStock()
        );
    }

}
