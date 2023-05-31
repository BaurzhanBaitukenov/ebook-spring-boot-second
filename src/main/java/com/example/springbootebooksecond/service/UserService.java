package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUsers();
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
    RegistrationDto findById(long id);
}