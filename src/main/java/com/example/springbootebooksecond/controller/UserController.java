package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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


}