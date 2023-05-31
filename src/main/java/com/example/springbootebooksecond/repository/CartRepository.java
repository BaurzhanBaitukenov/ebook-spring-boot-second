package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUserEmail(String username);
}
