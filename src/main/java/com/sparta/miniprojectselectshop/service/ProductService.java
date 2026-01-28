package com.sparta.miniprojectselectshop.service;

import com.sparta.miniprojectselectshop.dto.ProductResponseDto;
import com.sparta.miniprojectselectshop.dto.ProductRequestDto;
import com.sparta.miniprojectselectshop.entity.Product;
import com.sparta.miniprojectselectshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //등록하기
    @Transactional
    public void registerProduct(ProductRequestDto requestDto) {
        Product product = Product.create(requestDto.getProductName(),requestDto.getProductPrice(),requestDto.getProductStock());
        productRepository.save(product);
    }

    //조회하기
    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(Long id){

        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        return ProductResponseDto.from(product);
    }

    //전체 조회하기
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts(){

        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> results = products.stream().map(ProductResponseDto::from)
                .toList();

        return results;
    }

    //수정하기
    @Transactional
    public void updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        product.update(
                requestDto.getProductName(),
                requestDto.getProductPrice(),
                requestDto.getProductStock()
        ); // 해당 객체를 찾아서 받아온 데이터로 교체

    }

    // 삭제하기
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        productRepository.delete(product);
    }


}
