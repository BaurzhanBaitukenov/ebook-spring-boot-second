package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.models.ShoppingCart;
import com.example.springbootebooksecond.repository.CartRepository;
import com.example.springbootebooksecond.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CartImpl implements CartService {

    private final CartRepository cartRepository;

    public List<BookToShoppingCart> getBooksByUserId(long userId) {
        ShoppingCart shoppingCart = cartRepository.findByUserId(userId);
        if (shoppingCart != null) {
            return shoppingCart.getBookToShoppingCarts();
        }
        return Collections.emptyList();
    }


}
