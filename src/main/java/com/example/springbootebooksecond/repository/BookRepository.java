package com.example.springbootebooksecond.repository;

import com.example.springbootebooksecond.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT c from Book c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Book> searchBooks(String query);
}
