package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

//    @GetMapping("/profile")
//    public String getFakeProfile() {
//        return "users/user-fake-profile";
//    }
//
//
//    // get profile page by id
//    @GetMapping("/profile/{id}")
//    public String getProfilePage(@PathVariable("id") long id, Model model) {
//        RegistrationDto user = userService.findById(id);
//        model.addAttribute("user", user);
//        return "users/user-profile";
//    }

}