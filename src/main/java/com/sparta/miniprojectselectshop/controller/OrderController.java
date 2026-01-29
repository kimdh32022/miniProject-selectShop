package com.sparta.miniprojectselectshop.controller;

import com.sparta.miniprojectselectshop.dto.OrderRequestDto;
import com.sparta.miniprojectselectshop.dto.OrderResponseDto;
import com.sparta.miniprojectselectshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    // 상품 주문하기
    @PostMapping("/order")
    public void createOrder(@RequestBody OrderRequestDto requestDto) {
        orderService.createOrder(requestDto);
    }

    // 상품 조회하기
    @GetMapping("/order/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    // 상품 전체 조회
    @GetMapping("/orders")
    public Page<OrderResponseDto> getOrders(@PageableDefault(page= 0, size = 10) Pageable pageable) {

        return orderService.getOrders(pageable);
    }

}
