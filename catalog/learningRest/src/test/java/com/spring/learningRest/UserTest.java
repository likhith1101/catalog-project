package com.spring.learningRest;

import com.spring.learningRest.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testId() {
        // Test setting and getting the id
        int expectedId = 1;
        user.setId(expectedId);
        int actualId = user.getId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testName() {
        // Test setting and getting the name
        String expectedName = "John Doe";
        user.setName(expectedName);
        String actualName = user.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testPassword() {
        // Test setting and getting the password
        String expectedPassword = "secret";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();
        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testRole() {
        // Test setting and getting the role
        String expectedRole = "ROLE_USER";
        user.setRole(expectedRole);
        String actualRole = user.getRole();
        assertEquals(expectedRole, actualRole);
    }

     @Test
    public void testEquals() {
        User user1 = new User();
        User user2 = new User();

        assertEquals(user1, user2);
        assertEquals(user2, user1);
        assertEquals(user1, user1);

        user1.setId(1);
        assertTrue(!user1.equals(user2));
        assertTrue(!user2.equals(user1));
        assertTrue(!user1.equals(null));
        assertTrue(!user1.equals("string"));

        user2.setId(1);
        assertEquals(user1, user2);

        user2.setId(2);
        assertTrue(!user1.equals(user2));
    }

    @Test
    public void testHashCode() {
        User user1 = new User();
        User user2 = new User();

        assertEquals(user1.hashCode(), user2.hashCode());

        user1.setId(1);
        assertTrue(user1.hashCode() != user2.hashCode());
    }

   

   
}
