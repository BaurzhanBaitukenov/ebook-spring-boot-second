package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.repository.BookRepository;
import com.example.springbootebooksecond.repository.BookToShoppingCartRepository;
import com.example.springbootebooksecond.service.BookToShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookToShoppingCartImpl implements BookToShoppingCartService {

    private final BookToShoppingCartRepository bookToShoppingCartRepository;
    private final BookRepository bookRepository;

    @Override
    public BookToShoppingCart findBookById(long id) {
        return bookToShoppingCartRepository.findByBookId(id);
    }
}
