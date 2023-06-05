package com.example.springbootebooksecond.service.impl;


import com.example.springbootebooksecond.models.ShoppingCart;
import com.example.springbootebooksecond.repository.ShoppingCartRepository;
import com.example.springbootebooksecond.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ShoppingCartImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart createShoppingCart(String userEmail) {
        ShoppingCart cart = new ShoppingCart();
        cart.setUserEmail(userEmail);
        shoppingCartRepository.save(cart);
        return cart;
    }
}
