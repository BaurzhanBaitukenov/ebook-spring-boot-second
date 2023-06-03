package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.repository.BookRepository;
import com.example.springbootebooksecond.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookImpl implements BookService {

    private final BookRepository bookRepository;

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
    public void updateBook(BookDto clubDto) {
        Book club = mapToClub(clubDto);
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

    private Book mapToClub(BookDto clubDto) {
        if(clubDto == null) {
            return null;
        }

        Book club = Book.builder()
                .id(clubDto.getId())
                .author(clubDto.getAuthor())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .price(clubDto.getPrice())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();

        return club;
    }

    private BookDto mapToClubDto(Book club) {
        if(club == null) {
            return null;
        }

        BookDto clubDto = BookDto.builder()
                .id(club.getId())
                .author(club.getAuthor())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .price(club.getPrice())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();

        return clubDto;
    }
}
