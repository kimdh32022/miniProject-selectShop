package com.sparta.miniprojectselectshop.repository;

import com.sparta.miniprojectselectshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
