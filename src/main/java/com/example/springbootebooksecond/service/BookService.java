package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.Comment;


import java.util.List;

public interface BookService {
    List<BookDto> findAllBooks();
    Book saveBook(BookDto bookDto);
    BookDto findBookById(long bookId);
    Book findBookModelById(long bookId);
    void updateBook(BookDto bookDto);
    void updateBookModel(Book book);
    void delete(long bookId);
    List<BookDto> searchBooks(String query);
    List<Comment> getCommentsByBookId(Long bookId);
    Comment addCommentToBook(Long bookId, Comment comment);
    Comment deleteCommentToBook(Long bookId, Comment comment);

}