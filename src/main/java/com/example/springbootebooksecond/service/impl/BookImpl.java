package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.models.Comment;
import com.example.springbootebooksecond.repository.BookRepository;
import com.example.springbootebooksecond.repository.BookToShoppingCartRepository;
import com.example.springbootebooksecond.repository.CommentRepository;
import com.example.springbootebooksecond.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookToShoppingCartRepository bookToShoppingCartRepository;
    private final CommentRepository commentRepository;

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return books.stream().map(this::mapToBookDto).collect(Collectors.toList());
    }

    @Override
    public Book saveBook(BookDto bookDto) {
        Book book = mapToBook(bookDto);
        return bookRepository.save(book);
    }

    @Override
    public BookDto findBookById(long bookId) {
        Book book = bookRepository.findById(bookId).get();
        return mapToBookDto(book);
    }

    @Override
    public Book findBookModelById(long bookId) {
        return bookRepository.findById(bookId).get();
    }


    @Override
    public void updateBook(BookDto bookDto) {
        Book book = mapToBook(bookDto);
        bookRepository.save(book);
    }

    @Override
    public void updateBookModel(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<BookDto> searchBooks(String query) {
        List<Book> books = bookRepository.searchBooks(query);
        return books.stream().map(this::mapToBookDto).collect(Collectors.toList());
    }

    @Override
    public List<Comment> getCommentsByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        return book.getComments();
    }

    @Override
    public Comment addCommentToBook(Long bookId, Comment comment) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        comment.setBook(book);
        comment.setCreatedAt(LocalDateTime.now());

        book.getComments().add(comment);
        bookRepository.save(book);

        return comment;
    }

    @Override
    public Comment deleteCommentToBook(Long bookId, Comment comment) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        comment.setBook(book);
        comment.setCreatedAt(LocalDateTime.now());

        book.getComments().remove(comment);
        bookRepository.save(book);

        return comment;
    }


    private Book mapToBook(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())  // Set the id from the dto
                .author(bookDto.getAuthor())
                .title(bookDto.getTitle())
                .photoUrl(bookDto.getPhotoUrl())
                .content(bookDto.getContent())
                .price(bookDto.getPrice())
                .demoVersion(bookDto.getDemoVersion())
                .createdOn(bookDto.getCreatedOn())
                .updatedOn(bookDto.getUpdatedOn())
                .build();
    }

    private BookDto mapToBookDto(Book book) {

        return BookDto.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .photoUrl(book.getPhotoUrl())
                .content(book.getContent())
                .price(book.getPrice())
                .demoVersion(book.getDemoVersion())
                .createdOn(book.getCreatedOn())
                .updatedOn(book.getUpdatedOn())
                .build();
    }
}
