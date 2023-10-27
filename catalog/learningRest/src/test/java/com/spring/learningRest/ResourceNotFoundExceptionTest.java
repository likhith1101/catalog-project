package com.spring.learningRest;

import com.spring.learningRest.controller.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testConstructorAndGetMessage() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
    }
}

