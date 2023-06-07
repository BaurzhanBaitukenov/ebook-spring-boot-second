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
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookToShoppingCartRepository bookToShoppingCartRepository;

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> clubs = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Book saveBook(BookDto clubDto) {
        Book club = mapToClub(clubDto);
        return bookRepository.save(club);
    }

    @Override
    public BookDto findBookById(long clubId) {
        Book club = bookRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public Book findBookModelById(long clubId) {
        Book book = bookRepository.findById(clubId).get();
        return book;
    }


    @Override
    public void updateBook(BookDto clubDto) {
        Book club = mapToClub(clubDto);
        bookRepository.save(club);
    }

    @Override
    public void updateBookModel(Book club) {
        bookRepository.save(club);
    }

    @Override
    public void delete(long clubId) {
        bookRepository.deleteById(clubId);
    }

    @Override
    public List<BookDto> searchBooks(String query) {
        List<Book> clubs = bookRepository.searchBooks(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
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

    private Book mapToClub(BookDto clubDto) {
        return Book.builder()
                .id(clubDto.getId())  // Set the id from the dto
                .author(clubDto.getAuthor())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .price(clubDto.getPrice())
                .demoVersion(clubDto.getDemoVersion())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
    }

    private BookDto mapToClubDto(Book club) {

        return BookDto.builder()
                .id(club.getId())
                .author(club.getAuthor())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .price(club.getPrice())
                .demoVersion(club.getDemoVersion())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }
}
