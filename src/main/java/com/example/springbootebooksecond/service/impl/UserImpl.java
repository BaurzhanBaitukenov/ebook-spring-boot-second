package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.Comment;
import com.example.springbootebooksecond.models.Role;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.repository.*;
import com.example.springbootebooksecond.service.ShoppingCartService;
import com.example.springbootebooksecond.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;
    private final CommentRepository commentRepository;


    @Override
    public List<UserEntity> findAllUsers() {
        List<UserEntity> users = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return users;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setEmail(registrationDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        userEntity.setBalance(registrationDto.getBalance());
        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Arrays.asList(role));
        userRepository.save(userEntity);
        shoppingCartService.createShoppingCart(userEntity.getEmail());
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public int countLikesForUser(String userEmail) {
        Integer count = commentRepository.countLikesForUser(userEmail);
        return Optional.ofNullable(count).orElse(0);
    }


    public UserEntity getUserByEmail(String userEmail) {
        UserEntity user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + userEmail);
        }
        return user;
    }


    public RegistrationDto mapper(UserEntity userEntity) {
        if(userEntity == null) {
            return null;
        }

        RegistrationDto user = new RegistrationDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword(),userEntity.getBalance());
        return user;
    }
}