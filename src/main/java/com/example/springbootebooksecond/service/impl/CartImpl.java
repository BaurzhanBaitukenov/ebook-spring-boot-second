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
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        ShoppingCart shoppingCart = cartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if the book is already present in the shopping cart
        boolean isBookAlreadyInCart = shoppingCart.getBookToShoppingCarts().stream()
                .anyMatch(item -> item.getBook().getId() == bookId);

        if (isBookAlreadyInCart) {
            throw new IllegalArgumentException("Book already exists in the shopping cart");
        }

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
    public void deleteItemFromCart(long shoppingCartId, long bookId) {
        ShoppingCart cart = cartRepository.findById(shoppingCartId).get();

        List<BookToShoppingCart> cartItems = cart.getBookToShoppingCarts();
        cartItems.removeIf(item -> item.getBook().getId() == bookId);
        cartRepository.save(cart);
    }

    @Override
    public ShoppingCart findShoppingCartById(long id) {
        return cartRepository.findById(id).get();
    }


}
