package com.example.springbootebooksecond.controller;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    //register
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "registerAndLogin/register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }

        UserEntity existingUserUserName = userService.findByUsername(user.getUsername());
        if(existingUserUserName != null && existingUserUserName.getUsername() != null && !existingUserUserName.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }

        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "registerAndLogin/register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }

    //login
    @GetMapping("/login")
    public String loginPage() {
        return "registerAndLogin/login";
    }

}
