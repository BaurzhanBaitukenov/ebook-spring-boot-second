package com.example.springbootebooksecond.test;

import com.example.springbootebooksecond.dto.RegistrationDto;
import com.example.springbootebooksecond.models.Role;
import com.example.springbootebooksecond.models.UserEntity;
import com.example.springbootebooksecond.repository.RoleRepository;
import com.example.springbootebooksecond.repository.UserRepository;
import com.example.springbootebooksecond.service.ShoppingCartService;
import com.example.springbootebooksecond.service.impl.UserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserImplTest {
    private UserImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ShoppingCartService shoppingCartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserImpl(userRepository, roleRepository, passwordEncoder, shoppingCartService);
    }

    @Test
    void testFindAllUsers_ShouldReturnAllUsersInAscendingOrderById() {
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("user1");
        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setUsername("user2");
        List<UserEntity> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"))).thenReturn(expectedUsers);

        List<UserEntity> actualUsers = userService.findAllUsers();

        assertEquals(expectedUsers, actualUsers);
        verify(userRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Test
    void testSaveUser_ShouldSaveUserEntityAndCreateShoppingCart() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("testUser");
        registrationDto.setEmail("test@example.com");
        registrationDto.setPassword("password");

        Role role = new Role();
        role.setName("USER");
        when(roleRepository.findByName("USER")).thenReturn(role);

        userService.saveUser(registrationDto);

        verify(passwordEncoder, times(1)).encode(registrationDto.getPassword());
        verify(userRepository, times(1)).save(any(UserEntity.class));
        verify(shoppingCartService, times(1)).createShoppingCart(registrationDto.getEmail());
    }

    @Test
    void testFindByEmail_WithExistingEmail_ShouldReturnUserEntity() {
        String email = "test@example.com";
        UserEntity expectedUser = new UserEntity();
        expectedUser.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(expectedUser);

        UserEntity actualUser = userService.findByEmail(email);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testFindByUsername_WithExistingUsername_ShouldReturnUserEntity() {
        String username = "testUser";
        UserEntity expectedUser = new UserEntity();
        expectedUser.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        UserEntity actualUser = userService.findByUsername(username);

        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testMapper_WithNonNullUserEntity_ShouldReturnMappedRegistrationDto() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUser");
        userEntity.setEmail("test@example.com");
        userEntity.setPassword("password");

        RegistrationDto expectedRegistrationDto = new RegistrationDto(1L, "testUser", "test@example.com", "password");
        RegistrationDto actualRegistrationDto = userService.mapper(userEntity);

        assertEquals(expectedRegistrationDto, actualRegistrationDto);
    }

    @Test
    void testMapper_WithNullUserEntity_ShouldReturnNull() {
        UserEntity userEntity = null;

        RegistrationDto actualRegistrationDto = userService.mapper(userEntity);

        assertEquals(null, actualRegistrationDto);
    }
}
