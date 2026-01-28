package com.sparta.miniprojectselectshop.service;

import com.sparta.miniprojectselectshop.dto.OrderRequestDto;
import com.sparta.miniprojectselectshop.dto.OrderResponseDto;
import com.sparta.miniprojectselectshop.entity.Order;
import com.sparta.miniprojectselectshop.entity.Product;
import com.sparta.miniprojectselectshop.repository.OrderRepository;
import com.sparta.miniprojectselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;




    // 주문 만들기
    @Transactional
    public void createOrder(OrderRequestDto requestDto) {
        Product product = productRepository.findById(requestDto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        product.decreaseStock();

        Order order = new Order(product);
        orderRepository.save(order);
    }

    // 주문 하나 조회
    public OrderResponseDto getOrder(Long orderId){
        Order order =  orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("해당 주문은 없습니다."));

        Product product = order.getProduct();

        return new OrderResponseDto(
                order.getOrderId(),
                product.getProductName(),
                product.getProductPrice()
        );
    }


}
