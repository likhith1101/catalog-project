package com.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import com.spring.learningRest.business.JwtResponse;
import com.spring.learningRest.business.LoginBody;
import com.spring.learningRest.controller.APIAuthController;
import com.spring.learningRest.entity.User;
import com.spring.learningRest.service.UserService;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class APIAuthControllerTest {
    @InjectMocks
    private APIAuthController apiAuthController;

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);;
    }

    

    @Test
    public void testRegisterUser() {
        User newUser = new User();
        newUser.setName("testUser");
        newUser.setPassword("testPassword");
        newUser.setRole("USER");

        when(userService.existsByName(newUser.getName())).thenReturn(false);

        ResponseEntity<String> response = apiAuthController.registerUser(newUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }
}

