package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.models.ShoppingCart;

import java.util.List;

public interface CartService {
    List<BookToShoppingCart> getCart(String username);
    ShoppingCart addItemToCart(long shoppingCartId, long bookId);
    ShoppingCart findShoppingCartByUserName(String username);
    void deleteItemFromCart(long shoppingCartId, long bookId);
    ShoppingCart findShoppingCartById(long id);
}
