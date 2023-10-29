package org.spring.learningRest;



import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.spring.learningRest.entity.Feature;
import org.spring.learningRest.repository.FeatureRepository;
import org.spring.learningRest.service.FeatureService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FeatureServiceTest {

    @InjectMocks
    private FeatureService featureService;

    @Mock
    private FeatureRepository featureRepository;

    @Test
    void testEditFeature() {
        // Arrange
        Long featureId = 1L;
        Feature existingFeature = new Feature();
        existingFeature.setId(featureId);
        existingFeature.setName("Old Name");

        Feature updatedFeature = new Feature();
        updatedFeature.setId(featureId);
        updatedFeature.setName("New Name");

        when(featureRepository.findById(featureId)).thenReturn(Optional.of(existingFeature));
        when(featureRepository.save(existingFeature)).thenReturn(updatedFeature);

        // Act
        Feature result = featureService.editFeature(featureId, updatedFeature);

        // Assert
        assertNotNull(result);
        assertEquals("New Name", result.getName());
    }

    @Test
    void testEditFeatureFeatureNotFound() {
        // Arrange
        Long featureId = 1L;
        Feature updatedFeature = new Feature();

        when(featureRepository.findById(featureId)).thenReturn(Optional.empty());

        // Act
        Feature result = featureService.editFeature(featureId, updatedFeature);

        // Assert
        assertNull(result);
    }

    @Test
    void testAddFeature() {
        // Arrange
        Feature newFeature = new Feature();
        newFeature.setName("New Feature");

        when(featureRepository.save(any(Feature.class))).thenReturn(newFeature);

        // Act
        Feature result = featureService.addFeature(newFeature);

        // Assert
        assertNotNull(result);
        assertEquals("New Feature", result.getName());
    }

    @Test
    void testAddFeatureInvalidData() {
        // Arrange
        when(featureRepository.save(any(Feature.class))).thenReturn(null);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> featureService.addFeature(null));
    }
}
