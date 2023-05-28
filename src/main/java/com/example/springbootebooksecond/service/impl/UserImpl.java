package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.Role;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.repository.RoleRepository;
import com.example.springbootebooksecond.repository.UserRepository;
import com.example.springbootebooksecond.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Arrays.asList(role));
        userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
