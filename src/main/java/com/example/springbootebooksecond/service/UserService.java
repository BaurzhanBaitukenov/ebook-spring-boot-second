package com.example.springbootebooksecond.service;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
