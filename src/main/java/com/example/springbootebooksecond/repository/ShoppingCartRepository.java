package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
