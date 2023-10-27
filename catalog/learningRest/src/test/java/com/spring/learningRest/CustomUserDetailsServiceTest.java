package com.spring.learningRest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.learningRest.entity.User;
import com.spring.learningRest.service.CustomUserDetailsService;
import com.spring.learningRest.service.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;



@SpringBootTest
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserService userService;

    @Test
    public void testLoadUserByUsername() {
        // Arrange
        String username = "testuser";
        User myUserEntity = new User();
        myUserEntity.setName(username);
        myUserEntity.setPassword("password");
        myUserEntity.setRole("USER");

        when(userService.getByName(username)).thenReturn(Optional.of(myUserEntity));

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.isEnabled());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    public void testLoadUserByUsernameUserNotFound() {
        // Arrange
        String username = "nonexistentuser";

        when(userService.getByName(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(username));
    }
}
