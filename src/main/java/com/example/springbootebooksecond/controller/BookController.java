package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.service.BookService;
import com.example.springbootebooksecond.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clubs")
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    public BookController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    private String listBooks(Model model) {
        List<BookDto> clubs = bookService.findAllBooks();
        model.addAttribute("clubs", clubs);
        return "clubs/clubs-list";
    }

    //create
    @GetMapping("/new")
    public String createBookForm(Model model) {
        Book club = new Book();
        model.addAttribute("club", club);
        return "clubs/clubs-create";
    }

    @PostMapping("/new")
    public String saveBook(@ModelAttribute("club") BookDto clubDto) {
        bookService.saveBook(clubDto);
        return "redirect:/clubs";
    }

    //update
    @GetMapping("/{clubId}/edit")
    public String editBookForm(@PathVariable("clubId") long clubId, Model model) {
        BookDto club = bookService.findBookById(clubId);
        model.addAttribute("club", club);
        return "clubs/clubs-edit";
    }

    @PostMapping("/{clubId}/edit")
    public String editBook(@PathVariable("clubId") long clubId,
                           @Valid @ModelAttribute("club") BookDto clubDto,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "clubs/clubs-edit";
        }

        clubDto.setId(clubId);
        bookService.updateBook(clubDto);
        return "redirect:/clubs";
    }

    //Detail Page
    @GetMapping("/{clubId}")
    public String bookDetail(@PathVariable("clubId") long clubId, Model model) {
        BookDto clubDto = bookService.findBookById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs/clubs-detail";
    }

    //Delete
    @GetMapping("/{clubId}/delete")
    public String deleteBook(@PathVariable("clubId") long clubId) {
        bookService.delete(clubId);
        return "redirect:/clubs";
    }

    //searching
    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "query") String query, Model model) {
        List<BookDto> clubs = bookService.searchBooks(query);
        model.addAttribute("clubs", clubs);
        return "clubs/clubs-list";
    }
}
