package org.spring.learningRest;


import org.junit.jupiter.api.Test;
import org.spring.learningRest.controller.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotFoundExceptionTest {

    @Test
    void testNotFoundExceptionConstructor() {
        String message = "Resource not found";
        NotFoundException exception = new NotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}

