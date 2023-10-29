package org.spring.learningRest;


import org.junit.jupiter.api.Test;
import org.spring.learningRest.controller.exceptions.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceNotFoundExceptionTest {

    @Test
    void testConstructorAndGetMessage() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
    }
}

