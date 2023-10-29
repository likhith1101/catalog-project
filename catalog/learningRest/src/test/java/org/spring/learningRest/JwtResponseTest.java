package org.spring.learningRest;


import org.junit.jupiter.api.Test;
import org.spring.learningRest.business.JwtResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtResponseTest {

    @Test
    void testJwtResponseConstructor() {
        // Arrange
        String accessToken = "sampleAccessToken";
        String username = "sampleUsername";
        String role = "sampleRole";

        // Act
        JwtResponse jwtResponse = new JwtResponse(accessToken, username, role);

        // Assert
        assertEquals(accessToken, jwtResponse.getToken());
        assertEquals(username, jwtResponse.getUsername());
        assertEquals(role, jwtResponse.getRole());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        JwtResponse jwtResponse = new JwtResponse("accessToken", "username", "role");

        // Act and Assert
        assertEquals("accessToken", jwtResponse.getToken());
        assertEquals("username", jwtResponse.getUsername());
        assertEquals("role", jwtResponse.getRole());

        // Modify the values using setters
        jwtResponse.setToken("newAccessToken");
        jwtResponse.setUsername("newUsername");
        jwtResponse.setRole("newRole");

        // Check if setters worked correctly
        assertEquals("newAccessToken", jwtResponse.getToken());
        assertEquals("newUsername", jwtResponse.getUsername());
        assertEquals("newRole", jwtResponse.getRole());
    }

    @Test
    void testJwtResponseEquals() {
        JwtResponse jwtResponse1 = new JwtResponse("token1", "user1", "role1");
        JwtResponse jwtResponse2 = new JwtResponse("token1", "user1", "role1");
        JwtResponse jwtResponse3 = new JwtResponse("token1", "user1", "role1"); // Change attribute values here
    
        // Test equality
        assertEquals(jwtResponse1, jwtResponse2);
        assertEquals(jwtResponse2, jwtResponse1);
    
        // Test equality with the adjusted jwtResponse3
        assertEquals(jwtResponse1, jwtResponse3);
        assertEquals(jwtResponse2, jwtResponse3);
    }
    
    @Test
    void testJwtResponseHashCode() {
        JwtResponse jwtResponse1 = new JwtResponse("token1", "user1", "role1");
        JwtResponse jwtResponse2 = new JwtResponse("token1", "user1", "role1");

        // Check that the hash codes are equal for equal objects
        assertEquals(jwtResponse1.hashCode(), jwtResponse2.hashCode());
    }

   
   
}
