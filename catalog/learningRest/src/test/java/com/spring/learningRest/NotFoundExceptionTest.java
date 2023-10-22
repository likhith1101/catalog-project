package com.spring.learningRest;

import com.spring.learningRest.controller.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotFoundExceptionTest {

    @Test
    public void testNotFoundExceptionConstructor() {
        String message = "Resource not found";
        NotFoundException exception = new NotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}

