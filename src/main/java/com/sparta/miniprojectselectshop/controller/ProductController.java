package com.sparta.miniprojectselectshop.controller;

import com.sparta.miniprojectselectshop.dto.ProductResponseDto;
import com.sparta.miniprojectselectshop.dto.ProductRequestDto;
import com.sparta.miniprojectselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품 등록하기
    @PostMapping("/product")
    public void registerProduct(@RequestBody ProductRequestDto requestDto) {

        productService.registerProduct(requestDto);
    }

    // 상품 하나 조회
    @GetMapping("/product/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    // 상품 전체 조회
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() {
        return productService.getAllProducts();
    }

    // 상품 수정하기
    @PutMapping("/product/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestProductDto) {
        productService.updateProduct(id,requestProductDto);
    }

    // 상품 삭제하기
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
