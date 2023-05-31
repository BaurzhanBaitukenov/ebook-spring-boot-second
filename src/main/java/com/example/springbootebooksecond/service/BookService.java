package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.ShoppingCart;
import com.example.springbootebooksecond.models.UserEntity;

import java.util.List;

public interface BookService {
    List<BookDto> findAllBooks();
    Book saveBook(BookDto clubDto);
    BookDto findBookById(long clubId);
    void updateBook(BookDto clubDto);
    void delete(long clubId);
    List<BookDto> searchBooks(String query);
    ShoppingCart getCart(long userId);
}