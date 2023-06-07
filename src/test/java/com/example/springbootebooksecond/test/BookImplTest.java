package com.example.springbootebooksecond.test;


import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.repository.BookRepository;
import com.example.springbootebooksecond.repository.BookToShoppingCartRepository;
import com.example.springbootebooksecond.service.impl.BookImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BookImplTest {

    private BookImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookToShoppingCartRepository bookToShoppingCartRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookImpl(bookRepository, bookToShoppingCartRepository);
    }

    @Test
    public void testFindAllBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        List<Book> expectedBooks = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<BookDto> result = bookService.findAllBooks();

        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void testSaveBook() {
        BookDto bookDto = BookDto.builder()
                .author("Author 1")
                .title("Title 1")
                .photoUrl("photo1.jpg")
                .content("Content 1")
                .price(10)
                .build();

        Book savedBook = Book.builder()
                .id(1L)
                .author("Author 1")
                .title("Title 1")
                .photoUrl("photo1.jpg")
                .content("Content 1")
                .price(10)
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);
        Book result = bookService.saveBook(bookDto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getAuthor()).isEqualTo("Author 1");
        assertThat(result.getTitle()).isEqualTo("Title 1");
        assertThat(result.getPhotoUrl()).isEqualTo("photo1.jpg");
        assertThat(result.getContent()).isEqualTo("Content 1");
        assertThat(result.getPrice()).isEqualTo(10);
    }

    @Test
    public void testFindBookById() {
        Book book = Book.builder()
                .id(1L)
                .author("Author 1")
                .title("Title 1")
                .photoUrl("photo1.jpg")
                .content("Content 1")
                .price(10)
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDto result = bookService.findBookById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getAuthor()).isEqualTo("Author 1");
        assertThat(result.getTitle()).isEqualTo("Title 1");
        assertThat(result.getPhotoUrl()).isEqualTo("photo1.jpg");
        assertThat(result.getContent()).isEqualTo("Content 1");
        assertThat(result.getPrice()).isEqualTo(10);
    }


    @Test
    public void testDelete() {
        bookService.delete(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}

