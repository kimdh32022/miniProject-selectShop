package com.sparta.miniprojectselectshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDto {

    private  String productName;
    private int productPrice;
    private int productStock;

}
