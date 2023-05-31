package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.models.ShoppingCart;
import com.example.springbootebooksecond.repository.BookRepository;
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
    private final BookRepository bookRepository;

    public List<BookToShoppingCart> getCart(String userName) {
        ShoppingCart shoppingCart = cartRepository.findByUserEmail(userName);
        if (shoppingCart != null) {
            return shoppingCart.getBookToShoppingCarts();
        }
        return Collections.emptyList();
    }

    @Override
    public void addToCart(String username, Long bookId, int amount) {
        ShoppingCart shoppingCart = cartRepository.findByUserEmail(username);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (shoppingCart != null && book != null) {
            BookToShoppingCart bookToShoppingCart = new BookToShoppingCart();
            bookToShoppingCart.setShoppingCartId(shoppingCart.getId());
            bookToShoppingCart.setBook(book);
            bookToShoppingCart.setAmount(amount);

            shoppingCart.getBookToShoppingCarts().add(bookToShoppingCart);
            cartRepository.save(shoppingCart);
        }
    }


}
