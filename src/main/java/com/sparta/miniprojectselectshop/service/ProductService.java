package com.sparta.miniprojectselectshop.service;

import com.sparta.miniprojectselectshop.dto.ProductDto;
import com.sparta.miniprojectselectshop.dto.ProductRequestDto;
import com.sparta.miniprojectselectshop.entity.Product;
import com.sparta.miniprojectselectshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //등록하기
    public void registerProduct(ProductDto productDto) {
        Product product = new Product(
                productDto.getName(),
                productDto.getPrice(),
                productDto.getStock()
        );
        productRepository.save(product);
    }

    //조회하기
    public ProductDto getProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        return new ProductDto(
                product.getProductName(),
                product.getProductPrice(),
                product.getProductStock()
        ); //여기 리턴은 조회해서 이름과 가격, 재고를 알아야 하기 때문에 줘야 함.
    }

    //전체 조회하기
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();

        List<ProductDto> results = products.stream().map(product ->  new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getProductStock()
        ))
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
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        productRepository.delete(product);
    }


}
