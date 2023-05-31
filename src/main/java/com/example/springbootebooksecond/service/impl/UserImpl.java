package com.example.springbootebooksecond.service.impl;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.Book;
import com.example.springbootebooksecond.models.Role;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.repository.BookRepository;
import com.example.springbootebooksecond.repository.RoleRepository;
import com.example.springbootebooksecond.repository.UserRepository;
import com.example.springbootebooksecond.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public UserImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.bookRepository = bookRepository;
    }


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

    @Override
    public RegistrationDto findById(long id) {
        UserEntity user = userRepository.findById(id).get();
        return mapper(user);
    }

//    @Override
//    public void saveBook(UserEntity user, Book book) {
//        user.getSavedBooks().add(book);
//        book.getSavedByUsers().add(user);
//        userRepository.save(user);
//        bookRepository.save(book);
//    }


    public RegistrationDto mapper(UserEntity userEntity) {
        if(userEntity == null) {
            return null;
        }

        RegistrationDto user = new RegistrationDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(), userEntity.getPassword());
        return user;
    }
}