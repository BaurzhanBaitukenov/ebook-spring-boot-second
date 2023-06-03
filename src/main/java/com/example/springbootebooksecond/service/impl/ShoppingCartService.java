package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.models.ShoppingCart;
import com.example.springbootebooksecond.repository.ShoppingCartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart createShoppingCart(String userEmail) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserEmail(userEmail);
        shoppingCartRepository.save(cart);
        return cart;
    }
}
