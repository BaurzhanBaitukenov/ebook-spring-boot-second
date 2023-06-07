package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.models.BookToShoppingCart;

public interface BookToShoppingCartService {
    BookToShoppingCart findBookById(long id);
}
