package com.spring.learningRest;


import com.spring.learningRest.controller.FeatureController;
import com.spring.learningRest.controller.exceptions.ResourceNotFoundException;
import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Parameter;
import com.spring.learningRest.repository.FeatureRepository;
import com.spring.learningRest.service.FeatureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class FeatureControllerTest {

    private FeatureController featureController;
    private FeatureRepository featureRepository;
    private FeatureService featureService;

    @BeforeEach
    public void setUp() {
        featureRepository = mock(FeatureRepository.class);
        featureService = mock(FeatureService.class);

        featureController = new FeatureController(featureRepository, featureService);
    }

    @Test
    public void testGetAllFeatures() {
        // Arrange
        List<Feature> featureList = new ArrayList<>();
        when(featureRepository.findAll()).thenReturn(featureList);

        // Act
        List<Feature> result = featureController.getAllFeatures();

        // Assert
        assertEquals(featureList, result);
    }

    @Test
    public void testGetFeatureById() {
        // Arrange
        Long featureId = 1L;
        Feature feature = new Feature();
        when(featureRepository.findById(featureId)).thenReturn(Optional.of(feature));

        // Act
        ResponseEntity<Feature> response = featureController.getFeatureById(featureId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(feature, response.getBody());
    }

    @Test
    public void testGetFeatureByIdNotFound() {
        // Arrange
        Long featureId = 1L;
        when(featureRepository.findById(featureId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Feature> response = featureController.getFeatureById(featureId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddParameterToFeature() {
        // Arrange
        Long featureId = 1L;
        Feature feature = new Feature();
        Parameter newParameter = new Parameter();

        when(featureRepository.findById(featureId)).thenReturn(Optional.of(feature));

        // Act
        ResponseEntity<Feature> response = featureController.addParameterToFeature(featureId, newParameter);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(feature, response.getBody());
        assertEquals(1, feature.getParameters().size());
        assertEquals(newParameter, feature.getParameters().get(0));
    }

    @Test
    public void testAddParameterToFeatureNotFound() {
        // Arrange
        Long featureId = 1L;
        when(featureRepository.findById(featureId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Feature> response = featureController.addParameterToFeature(featureId, new Parameter());

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddFeature() {
        // Arrange
        Feature newFeature = new Feature();
        Feature savedFeature = new Feature();
        when(featureService.addFeature(newFeature)).thenReturn(savedFeature);

        // Act
        ResponseEntity<Feature> response = featureController.addFeature(newFeature);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedFeature, response.getBody());
    }

    @Test
    public void testAddFeatureError() {
        // Arrange
        Feature newFeature = new Feature();
        when(featureService.addFeature(newFeature)).thenThrow(new IllegalArgumentException("Invalid feature"));

        // Act
        ResponseEntity<Feature> response = featureController.addFeature(newFeature);

        // Assert
        assertEquals("Error", response.getBody().getName());
    }

    @Test
    public void testEditFeature() {
        // Arrange
        Long featureId = 1L;
        Feature updatedFeature = new Feature();
        updatedFeature.setName("UpdatedFeature");

        Feature existingFeature = new Feature();
        when(featureService.editFeature(eq(featureId), any())).thenReturn(existingFeature);

        // Act
        ResponseEntity<Feature> response = featureController.editFeature(featureId, updatedFeature);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingFeature, response.getBody());
    }

    @Test
    public void testEditFeatureNotFound() {
        // Arrange
        Long featureId = 1L;
        Feature updatedFeature = new Feature();
        when(featureService.editFeature(eq(featureId), any())).thenReturn(null);

        // Act
        ResponseEntity<Feature> response = featureController.editFeature(featureId, updatedFeature);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteFeature() {
        // Arrange
        Long featureId = 1L;
        Feature feature = new Feature();
        when(featureRepository.findById(featureId)).thenReturn(Optional.of(feature));

        // Act
        ResponseEntity<Void> response = featureController.deleteFeature(featureId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeleteFeatureNotFound() {
        // Arrange
        Long featureId = 1L;
        when(featureRepository.findById(featureId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Void> response = featureController.deleteFeature(featureId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetParametersByFeatureId() {
        // Arrange
        Long featureId = 1L;
        Feature feature = new Feature();
        Parameter parameter1 = new Parameter();
        Parameter parameter2 = new Parameter();
        feature.addParameter(parameter1);
        feature.addParameter(parameter2);

        when(featureRepository.findById(featureId)).thenReturn(Optional.of(feature));

        // Act
        List<Parameter> result = featureController.getParametersByFeatureId(featureId);

        // Assert
        assertEquals(2, result.size());
        assertEquals(parameter1, result.get(0));
        assertEquals(parameter2, result.get(1));
    }

    @Test
    public void testGetParametersByFeatureIdNotFound() {
        // Arrange
        Long featureId = 1L;
        when(featureRepository.findById(featureId)).thenReturn(Optional.empty());

        // Act and Assert
        try {
            featureController.getParametersByFeatureId(featureId);
        } catch (ResourceNotFoundException ex) {
            assertEquals("Feature not found with ID: " + featureId, ex.getMessage());
        }
    }
}
