package com.spring.learningRest;

import com.spring.learningRest.business.LoginBody;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginBodyTest {

    @Test
    public void testLoginBodyConstructor() {
        // Create a LoginBody object
        LoginBody loginBody = new LoginBody("testUser", "testPassword");

        // Verify the values are set correctly
        assertEquals("testUser", loginBody.getUsername());
        assertEquals("testPassword", loginBody.getPassword());
    }

    @Test
    public void testLoginBodySetters() {
        // Create a LoginBody object
        LoginBody loginBody = new LoginBody(null, null);

        // Set values using setters
        loginBody.setUsername("newUser");
        loginBody.setPassword("newPassword");

        // Verify the values are set correctly
        assertEquals("newUser", loginBody.getUsername());
        assertEquals("newPassword", loginBody.getPassword());
    }

    @Test
    public void testLoginBodyEquals() {
        LoginBody loginBody1 = new LoginBody("user1", "pass1");
        LoginBody loginBody2 = new LoginBody("user1", "pass1");
        LoginBody loginBody3 = new LoginBody("user2", "pass2");

        // Test equality
        assertTrue(loginBody1.equals(loginBody2));
        assertTrue(loginBody2.equals(loginBody1));

        // Test inequality
        assertTrue(!loginBody1.equals(loginBody3));
        assertTrue(!loginBody2.equals(loginBody3));
    }

    @Test
    public void testLoginBodyHashCode() {
        LoginBody loginBody1 = new LoginBody("user1", "pass1");
        LoginBody loginBody2 = new LoginBody("user1", "pass1");

        // Check that the hash codes are equal for equal objects
        assertEquals(loginBody1.hashCode(), loginBody2.hashCode());
    }

    

   
    
    
}

