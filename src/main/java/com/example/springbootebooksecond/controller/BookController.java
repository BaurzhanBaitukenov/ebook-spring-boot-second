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
@RequestMapping("/clubs")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final CartService cartService;


    @GetMapping
    private String listBooks(Model model) {
        List<BookDto> clubs = bookService.findAllBooks();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ShoppingCart cart = cartService.findShoppingCartByUserName(username);

        model.addAttribute("carts", cart);
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
        Book club = bookService.findBookModelById(clubId);
        model.addAttribute("club", club);
        return "clubs/clubs-edit";
    }


    @PostMapping("/{clubId}/edit")
    public String editBook(@PathVariable("clubId") long clubId,
                           @Valid @ModelAttribute("club") Book club,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "clubs/clubs-edit";
        }
        club.setId(clubId);
        bookService.updateBookModel(club);
        return "redirect:/clubs";
    }

    //Detail Page
    @GetMapping("/{clubId}")
    public String bookDetail(@PathVariable("clubId") long clubId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ShoppingCart cart = cartService.findShoppingCartByUserName(username);

        model.addAttribute("carts", cart);

        BookDto clubDto = bookService.findBookById(clubId);
        model.addAttribute("club", clubDto);


        List<Comment> comments = bookService.getCommentsByBookId(clubId);
        model.addAttribute("comments", comments);
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
