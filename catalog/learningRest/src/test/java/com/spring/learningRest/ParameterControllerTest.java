package com.spring.learningRest;


import com.spring.learningRest.controller.ParameterController;
import com.spring.learningRest.entity.Parameter;
import com.spring.learningRest.repository.ParameterRepository;
import com.spring.learningRest.service.ParameterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ParameterControllerTest {
   
@Autowired
    private ParameterController parameterController;

    @Autowired
    private ParameterRepository parameterRepository;

    @Autowired
    private ParameterService parameterService;

    @BeforeEach
    public void setUp() {
        parameterRepository = mock(ParameterRepository.class);
        parameterService = mock(ParameterService.class);

        parameterController = new ParameterController(parameterRepository, parameterService);
    }

    @Test
    public void testGetAllParameters() {
        // Arrange
        List<Parameter> parameterList = new ArrayList<>();
        when(parameterRepository.findAll()).thenReturn(parameterList);

        // Act
        List<Parameter> result = parameterController.getAllParameters();

        // Assert
        assertEquals(parameterList, result);
    }

    @Test
    public void testAddParameter() {
        // Arrange
        Parameter newParameter = new Parameter();
        Parameter savedParameter = new Parameter();
        when(parameterService.addParameter(newParameter)).thenReturn(savedParameter);

        // Act
        ResponseEntity<Parameter> response = parameterController.addParameter(newParameter);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedParameter, response.getBody());
    }

    @Test
    public void testAddParameterError() {
        // Arrange
        Parameter newParameter = new Parameter();
        when(parameterService.addParameter(newParameter)).thenThrow(new IllegalArgumentException("Invalid parameter"));

        // Act
        ResponseEntity<Parameter> response = parameterController.addParameter(newParameter);

        // Assert
        assertEquals("Error", response.getBody().getName());
        assertEquals("Error", response.getBody().getInternalName());
        assertEquals("Error", response.getBody().getDetails());
    }

    @Test
    public void testEditParameter() {
        // Arrange
        Long parameterId = 1L;
        Parameter updatedParameter = new Parameter();
        updatedParameter.setName("UpdatedParameter");

        Parameter existingParameter = new Parameter();
        when(parameterService.editParameter(eq(parameterId), any())).thenReturn(existingParameter);

        // Act
        ResponseEntity<Parameter> response = parameterController.editParameter(parameterId, updatedParameter);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingParameter, response.getBody());
    }

    @Test
    public void testEditParameterNotFound() {
        // Arrange
        Long parameterId = 1L;
        Parameter updatedParameter = new Parameter();
        when(parameterService.editParameter(eq(parameterId), any())).thenReturn(null);

        // Act
        ResponseEntity<Parameter> response = parameterController.editParameter(parameterId, updatedParameter);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
