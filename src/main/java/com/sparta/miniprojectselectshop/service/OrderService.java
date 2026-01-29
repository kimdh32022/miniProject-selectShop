package com.sparta.miniprojectselectshop.service;

import com.sparta.miniprojectselectshop.dto.OrderRequestDto;
import com.sparta.miniprojectselectshop.dto.OrderResponseDto;
import com.sparta.miniprojectselectshop.entity.Order;
import com.sparta.miniprojectselectshop.entity.Product;
import com.sparta.miniprojectselectshop.repository.OrderRepository;
import com.sparta.miniprojectselectshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        // 재고 감소 시키고 주문 재고가 없다면 주문자체가 안되게
        product.decreaseStock();

        // 주문 생성 => Entity 객체에서 정적 팩토리 메서드 사용
        Order order = Order.create(product);

        orderRepository.save(order);
    }

    // 주문 하나 조회
    public OrderResponseDto getOrder(Long orderId){
        Order order =  orderRepository.findById(orderId)
                .orElseThrow(()-> new IllegalArgumentException("해당 주문은 없습니다."));

       return OrderResponseDto.from(order);
    }

    // 주문 목록 조회
    @Transactional
    public Page<OrderResponseDto> getOrders(Pageable pageable){

        Page<Order> orders = orderRepository.findAll(pageable);

        return orders.map(OrderResponseDto::from);
    }



}
