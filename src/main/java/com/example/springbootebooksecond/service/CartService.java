package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.models.ShoppingCart;

import java.util.List;

public interface CartService {
    List<BookToShoppingCart> getCart(String username);
    void addToCart(String username, Long bookId, int amount);
}
