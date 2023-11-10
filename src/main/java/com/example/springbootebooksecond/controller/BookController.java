package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.dto.BookDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.Comment;
import com.example.springbootebooksecond.models.ShoppingCart;
import com.example.springbootebooksecond.service.BookService;
import com.example.springbootebooksecond.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CartService cartService;


    @GetMapping
    private String listBooks(Model model) {
        List<BookDto> books = bookService.findAllBooks();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ShoppingCart cart = cartService.findShoppingCartByUserName(username);

        model.addAttribute("carts", cart);
        model.addAttribute("books", books);
        return "books/books-list";
    }

    //create
    @GetMapping("/new")
    public String createBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/books-create";
    }

    @PostMapping("/new")
    public String saveBook(@ModelAttribute("book") BookDto bookDto) {
        bookService.saveBook(bookDto);
        return "redirect:/books";
    }

    //update
    @GetMapping("/{bookId}/edit")
    public String editBookForm(@PathVariable("bookId") long bookId, Model model) {
        Book book = bookService.findBookModelById(bookId);
        model.addAttribute("book", book);
        return "books/books-edit";
    }


    @PostMapping("/{bookId}/edit")
    public String editBook(@PathVariable("bookId") long bookId,
                           @Valid @ModelAttribute("book") Book book,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "books/books-edit";
        }
        book.setId(bookId);
        bookService.updateBookModel(book);
        return "redirect:/books";
    }

    //Detail Page
    @GetMapping("/{bookId}")
    public String bookDetail(@PathVariable("bookId") long bookId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ShoppingCart cart = cartService.findShoppingCartByUserName(username);

        model.addAttribute("carts", cart);

        BookDto bookDto = bookService.findBookById(bookId);
        model.addAttribute("book", bookDto);


        List<Comment> comments = bookService.getCommentsByBookId(bookId);
        model.addAttribute("comments", comments);
        return "books/books-detail";
    }

    //Delete
    @GetMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId") long bookId) {
        bookService.delete(bookId);
        return "redirect:/books";
    }

    //searching
    @GetMapping("/search")
    public String searchBook(@RequestParam(value = "query") String query, Model model) {
        List<BookDto> books = bookService.searchBooks(query);
        model.addAttribute("books", books);
        return "books/books-list";
    }

}
