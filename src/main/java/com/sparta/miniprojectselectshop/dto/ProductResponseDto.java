package com.sparta.miniprojectselectshop.dto;

import com.sparta.miniprojectselectshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

//        return new ProductResponseDto(
//                product.getProductId(),
//                product.getProductName(),
//                product.getProductPrice(),
//                product.getProductStock()
//        );
        return ProductResponseDto.builder()
                .id(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productStock(product.getProductStock())
                .build();
    }

}
