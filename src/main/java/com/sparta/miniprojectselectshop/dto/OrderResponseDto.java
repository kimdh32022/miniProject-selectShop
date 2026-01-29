package com.sparta.miniprojectselectshop.dto;

import com.sparta.miniprojectselectshop.entity.Order;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class OrderResponseDto {
    private Long orderId;
    private String productName;
    private int productPrice;



    public static  OrderResponseDto from(Order order){

//       return new OrderResponseDto(
//               order.getOrderId(),
//               order.getProduct().getProductName(),
//               order.getProduct().getProductPrice()
//       ); // 빌더사용 Why?  생성자 사용으로 만드는 객체는 파라미터에 변수 위치가 중요!!
        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .productName(order.getProduct().getProductName())
                .productPrice(order.getProduct().getProductPrice())
                .build();
    }
}
