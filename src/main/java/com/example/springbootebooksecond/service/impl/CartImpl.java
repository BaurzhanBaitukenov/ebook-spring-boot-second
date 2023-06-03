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
    public ShoppingCart addItemToCart(long shoppingCartId, long bookId) {
        ShoppingCart shoppingCart = cartRepository.findById(shoppingCartId).orElseThrow(() -> new RuntimeException("Shopping cart not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        BookToShoppingCart bookToShoppingCart = new BookToShoppingCart();
        bookToShoppingCart.setShoppingCartId(shoppingCartId);
        bookToShoppingCart.setBook(book);

        shoppingCart.getBookToShoppingCarts().add(bookToShoppingCart);

        return cartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findShoppingCartByUserName(String username) {
        return cartRepository.findByUserEmail(username);
    }

    @Override
    public ShoppingCart findShoppingCartById(long id) {
        return cartRepository.findById(id).get();
    }


}
