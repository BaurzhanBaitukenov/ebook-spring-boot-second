package com.example.springbootebooksecond.test;

import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.models.ShoppingCart;
import com.example.springbootebooksecond.repository.BookRepository;
import com.example.springbootebooksecond.repository.CartRepository;
import com.example.springbootebooksecond.service.impl.CartImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CartImplTest {

    private CartImpl cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cartService = new CartImpl(cartRepository, bookRepository);
    }

    @Test
    public void testGetCart_WithValidUserName_ShouldReturnCartItems() {
        // Arrange
        String userName = "test@example.com";
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserEmail(userName);

        Book book1 = new Book();
        book1.setId(1L);
        BookToShoppingCart item1 = new BookToShoppingCart();
        item1.setBook(book1);

        Book book2 = new Book();
        book2.setId(2L);
        BookToShoppingCart item2 = new BookToShoppingCart();
        item2.setBook(book2);

        shoppingCart.setBookToShoppingCarts(List.of(item1, item2));

        when(cartRepository.findByUserEmail(userName)).thenReturn(shoppingCart);

        // Act
        List<BookToShoppingCart> cartItems = cartService.getCart(userName);

        // Assert
        Assertions.assertEquals(2, cartItems.size());
        Assertions.assertEquals(item1, cartItems.get(0));
        Assertions.assertEquals(item2, cartItems.get(1));
    }

    @Test
    public void testGetCart_WithInvalidUserName_ShouldReturnEmptyList() {
        // Arrange
        String userName = "nonexistent@example.com";

        when(cartRepository.findByUserEmail(userName)).thenReturn(null);

        // Act
        List<BookToShoppingCart> cartItems = cartService.getCart(userName);

        // Assert
        Assertions.assertEquals(Collections.emptyList(), cartItems);
    }

    @Test
    public void testAddItemToCart_WithValidInputs_ShouldAddItemToCart() {
        // Arrange
        long shoppingCartId = 1L;
        long bookId = 1L;

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartId);

        Book book = new Book();
        book.setId(bookId);

        when(cartRepository.findById(shoppingCartId)).thenReturn(Optional.of(shoppingCart));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        ShoppingCart result = cartService.addItemToCart(shoppingCartId, bookId);

        // Assert
        Assertions.assertEquals(shoppingCart, result);
        Assertions.assertEquals(1, shoppingCart.getBookToShoppingCarts().size());
        Assertions.assertEquals(book, shoppingCart.getBookToShoppingCarts().get(0).getBook());
    }

    @Test
    public void testAddItemToCart_WithInvalidShoppingCartId_ShouldThrowException() {
        // Arrange
        long shoppingCartId = 1L;
        long bookId = 1L;

        when(cartRepository.findById(shoppingCartId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> cartService.addItemToCart(shoppingCartId, bookId));
    }

    @Test
    public void testAddItemToCart_WithInvalidBookId_ShouldThrowException() {
        // Arrange
        long shoppingCartId = 1L;
        long bookId = 1L;

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartId);

        when(cartRepository.findById(shoppingCartId)).thenReturn(Optional.of(shoppingCart));
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> cartService.addItemToCart(shoppingCartId, bookId));
    }

    @Test
    public void testDeleteItemFromCart_WithExistingItem_ShouldRemoveItemFromCart() {
        // Arrange
        long shoppingCartId = 1L;
        long bookId = 1L;

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartId);

        BookToShoppingCart item1 = new BookToShoppingCart();
        item1.setId(1L);
        item1.setBook(new Book());

        BookToShoppingCart item2 = new BookToShoppingCart();
        item2.setId(2L);
        item2.setBook(new Book());

        List<BookToShoppingCart> cartItems = new ArrayList<>();
        cartItems.add(item1);
        cartItems.add(item2);

        shoppingCart.setBookToShoppingCarts(cartItems);

        when(cartRepository.findById(shoppingCartId)).thenReturn(Optional.of(shoppingCart));

        // Act
        cartService.deleteItemFromCart(shoppingCartId, bookId);

        // Assert
        Assertions.assertEquals(1, shoppingCart.getBookToShoppingCarts().size());
        Assertions.assertFalse(shoppingCart.getBookToShoppingCarts().stream().anyMatch(item -> item.getId() == bookId));
        verify(cartRepository, times(1)).save(shoppingCart);
    }

    @Test
    public void testDeleteItemFromCart_WithNonExistingItem_ShouldNotModifyCart() {
        // Arrange
        long shoppingCartId = 1L;
        long bookId = 1L;

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartId);

        BookToShoppingCart item1 = new BookToShoppingCart();
        item1.setId(2L);
        item1.setBook(new Book());

        List<BookToShoppingCart> cartItems = new ArrayList<>();
        cartItems.add(item1);

        shoppingCart.setBookToShoppingCarts(cartItems);

        when(cartRepository.findById(shoppingCartId)).thenReturn(Optional.of(shoppingCart));

        // Act
        cartService.deleteItemFromCart(shoppingCartId, bookId);

        // Assert
        Assertions.assertEquals(1, shoppingCart.getBookToShoppingCarts().size());
        verify(cartRepository, times(1)).save(shoppingCart);
    }
}
