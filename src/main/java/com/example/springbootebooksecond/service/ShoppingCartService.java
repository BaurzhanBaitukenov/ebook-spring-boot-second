package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart createShoppingCart(String email);
}
