package com.sparta.miniprojectselectshop.repository;

import com.sparta.miniprojectselectshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
