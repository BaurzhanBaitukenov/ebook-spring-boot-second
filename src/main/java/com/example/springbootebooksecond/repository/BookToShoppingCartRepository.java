package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.BookToShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookToShoppingCartRepository extends JpaRepository<BookToShoppingCart, Long> {
    BookToShoppingCart findByBookId(long id);
}
