package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT c FROM Book c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Book> searchBooks(String query);

    @Transactional
    void deleteBookById(long bookId);
}
