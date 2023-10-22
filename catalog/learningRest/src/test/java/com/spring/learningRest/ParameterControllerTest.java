package com.spring.learningRest;

import com.spring.learningRest.controller.ParameterController;
import com.spring.learningRest.entity.Parameter;
import com.spring.learningRest.repository.ParameterRepository;
import com.spring.learningRest.service.ParameterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParameterControllerTest {

    @Autowired
    private ParameterController parameterController;

    @MockBean
    private ParameterRepository parameterRepository;

    @MockBean
    private ParameterService parameterService;

    @Test
    void testGetAllParameters() {
        List<Parameter> mockParameters = new ArrayList<>();
        // Add some mock parameters to the list

        when(parameterRepository.findAll()).thenReturn(mockParameters);

        List<Parameter> response = parameterController.getAllParameters();

        assertEquals(mockParameters, response);
    }

    @Test
    void testGetParameterById() {
        Long parameterId = 1L;
        Parameter mockParameter = new Parameter();
        mockParameter.setId(parameterId);
        // Set other properties for the mock parameter

        when(parameterRepository.findById(parameterId)).thenReturn(java.util.Optional.of(mockParameter));

        ResponseEntity<Parameter> response = parameterController.getParameterById(parameterId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockParameter, response.getBody());
    }

    @Test
    void testGetParameterByIdNotFound() {
        Long parameterId = 2L;

        when(parameterRepository.findById(parameterId)).thenReturn(java.util.Optional.empty());

        ResponseEntity<Parameter> response = parameterController.getParameterById(parameterId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddParameter() {
        Parameter mockParameter = new Parameter();
        // Set properties for the mock parameter

        when(parameterService.addParameter(any(Parameter.class))).thenReturn(mockParameter);

        ResponseEntity<Parameter> response = parameterController.addParameter(mockParameter);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockParameter, response.getBody());
    }

    @Test
    void testAddParameterError() {
        when(parameterService.addParameter(any(Parameter.class))).thenThrow(new IllegalArgumentException("Invalid parameter"));

        ResponseEntity<Parameter> response = parameterController.addParameter(new Parameter());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Error", response.getBody().getName());
    }

    @Test
    void testEditParameter() {
        Long parameterId = 1L;
        Parameter updatedParameter = new Parameter();
        updatedParameter.setId(parameterId);
        // Set other properties for the updated parameter

        when(parameterService.editParameter(parameterId, updatedParameter)).thenReturn(updatedParameter);

        ResponseEntity<Parameter> response = parameterController.editParameter(parameterId, updatedParameter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedParameter, response.getBody());
    }

    @Test
    void testEditParameterNotFound() {
        Long parameterId = 2L;

        when(parameterService.editParameter(parameterId, new Parameter())).thenReturn(null);

        ResponseEntity<Parameter> response = parameterController.editParameter(parameterId, new Parameter());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteParameter() {
        Long parameterId = 1L;
        Parameter mockParameter = new Parameter();
        // Set properties for the mock parameter

        when(parameterRepository.findById(parameterId)).thenReturn(java.util.Optional.of(mockParameter));

        ResponseEntity<Void> response = parameterController.deleteParameter(parameterId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteParameterNotFound() {
        Long parameterId = 2L;

        when(parameterRepository.findById(parameterId)).thenReturn(java.util.Optional.empty());

        ResponseEntity<Void> response = parameterController.deleteParameter(parameterId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}



