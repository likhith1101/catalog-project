package org.spring.learningRest;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.spring.learningRest.controller.exceptions.NotFoundException;
import org.spring.learningRest.entity.User;
import org.spring.learningRest.repository.UserRepository;
import org.spring.learningRest.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository);
    }

    @Test
    void testAuthenticate_WhenUserExistsAndCorrectPassword_ExpectUserReturned() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode(password);

        User user = new User();
        user.setName(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByName(username)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.authenticate(username, password);

        // Assert
    }

    @Test
    void testAuthenticate_WhenUserDoesNotExist_ExpectNotFoundException() {
        // Arrange
        String username = "nonExistentUser";
        String password = "testPassword";

        when(userRepository.findByName(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> userService.authenticate(username, password));
    }

    @Test
    void testAuthenticate_WhenUserExistsButWrongPassword_ExpectEmptyOptional() {
        // Arrange
        String username = "testUser";
        String correctPassword = "correctPassword";
        String providedPassword = "wrongPassword";
        String encodedPassword = "{bcrypt}" + passwordEncoder.encode(correctPassword);

        User user = new User();
        user.setName(username);
        user.setPassword(encodedPassword);

        when(userRepository.findByName(username)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.authenticate(username, providedPassword);

        // Assert
        assertTrue(result.isEmpty());
    }

    // Add more test methods for other UserService functionalities

    @Test
    void testCreateUser_WithValidUser_ExpectUserCreated() {
        // Arrange
        User user = new User();
        user.setName("newUser");
        user.setPassword("password");

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User createdUser = userService.create(user);

        // Assert
        assertNotNull(createdUser);
        assertEquals("newUser", createdUser.getName());
        assertTrue(createdUser.getPassword().startsWith("{bcrypt}"));
    }

    @Test
    void testCreateUser_WithDuplicateUsername_ExpectUserNotCreated() {
        // Arrange
        User existingUser = new User();
        existingUser.setName("existingUser");
        existingUser.setPassword("password");

        User newUser = new User();
        newUser.setName("existingUser"); // Same username as existing user
        newUser.setPassword("newPassword");

        when(userRepository.findByName(existingUser.getName())).thenReturn(Optional.of(existingUser));

        // Act
        User createdUser = userService.create(newUser);

        // Assert
        assertNull(createdUser);
    }

    @Test
    void testGetUserById_WithValidId_ExpectUserReturned() {
        // Arrange
        int userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("testUser");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
        assertEquals("testUser", result.get().getName());
    }

    @Test
    void testGetUserById_WithInvalidId_ExpectEmptyOptional() {
        // Arrange
        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.getById(userId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testExistsByName_WithExistingUsername_ExpectTrue() {
        // Arrange
        String username = "existingUser";

        when(userRepository.existsByName(username)).thenReturn(true);

        // Act
        boolean exists = userService.existsByName(username);

        // Assert
        assertTrue(exists);
    }

    @Test
    void testExistsByName_WithNonExistentUsername_ExpectFalse() {
        // Arrange
        String username = "nonExistentUser";

        when(userRepository.existsByName(username)).thenReturn(false);

        // Act
        boolean exists = userService.existsByName(username);

        // Assert
        assertFalse(exists);
    }

}
