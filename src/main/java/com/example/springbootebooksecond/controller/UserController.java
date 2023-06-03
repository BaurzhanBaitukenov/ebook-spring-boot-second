package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.BookToShoppingCart;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.service.CartService;
import com.example.springbootebooksecond.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final CartService cartService;


    //list of all users
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserEntity> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users/users-list";
    }


    // user page detail
    @GetMapping("/profile/{username}")
    public String getProfilePage(@PathVariable("username") String username, Model model) {
        UserEntity user = userService.findByEmail(username);
        model.addAttribute("user", user);
        return "users/user-profile";
    }


    // user saved books
    @GetMapping("/book/{email}")
    public String getCartPage(@PathVariable("email") String email, Model model) {
        List<BookToShoppingCart> shoppingCart = cartService.getCart(email);
        List<Book> books = shoppingCart.stream()
                .map(BookToShoppingCart::getBook)
                .collect(Collectors.toList());

        int totalPrice = books.stream()
                .mapToInt(Book::getPrice)
                .sum();


        model.addAttribute("books", books);
        model.addAttribute("totalPrice", totalPrice);
        return "clubs/shopping-cart";
    }


    @PostMapping("/book/cart")
    public String addToCart(@RequestParam("shoppingCartId") long shoppingCartId,
                            @RequestParam("bookId") long bookId) {

        cartService.addItemToCart(shoppingCartId, bookId);
        return "redirect:/clubs";
    }


}