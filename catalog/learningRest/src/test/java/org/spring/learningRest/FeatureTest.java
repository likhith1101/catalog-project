package org.spring.learningRest;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.learningRest.entity.Feature;
import org.spring.learningRest.entity.Parameter;
import org.spring.learningRest.entity.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeatureTest {
    private Feature feature;

    @BeforeEach
    public void setUp() {
        feature = new Feature();
    }

    @Test
    void testConstructor() {
        assertNull(feature.getFeatureId());
        assertNull(feature.getName());
        assertNull(feature.getInternalName());
        assertNull(feature.getDetails());
        assertNull(feature.getParameters());
    }

    @Test
    void testSetAndGetId() {
        // Arrange
        Long featureId = 1L;

        // Act
        feature.setFeatureId(featureId);

        // Assert
        assertEquals(featureId, feature.getFeatureId());
    }

    @Test
    void testSetName() {
        // Arrange
        String name = "FeatureName";

        // Act
        feature.setName(name);

        // Assert
        assertEquals(name, feature.getName());
    }

    @Test
    void testSetInternalName() {
        // Arrange
        String internalName = "InternalName";

        // Act
        feature.setInternalName(internalName);

        // Assert
        assertEquals(internalName, feature.getInternalName());
    }

    @Test
    void testSetDetails() {
        // Arrange
        String details = "FeatureDetails";

        // Act
        feature.setDetails(details);

        // Assert
        assertEquals(details, feature.getDetails());
    }

    @Test
    void testSetProduct() {
        Product product = new Product();
        Feature feature = new Feature(); // Assuming YourClass has a method setProduct()

        feature.setProduct(product);

    }
    @Test
    void testGettersAndSetters() {
        // Arrange
        feature.setFeatureId(1L);
        feature.setName("FeatureName");
        feature.setInternalName("InternalName");
        feature.setDetails("FeatureDetails");

        Product product = new Product();
        feature.setProduct(product);

        Parameter parameter1 = new Parameter();
        Parameter parameter2 = new Parameter();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);
        feature.setParameters(parameters);

        // Act
        Long featureId = feature.getFeatureId();
        String name = feature.getName();
        String internalName = feature.getInternalName();
        String details = feature.getDetails();
        List<Parameter> retrievedParameters = feature.getParameters();

        // Assert
        assertEquals(1L, featureId);
        assertEquals("FeatureName", name);
        assertEquals("InternalName", internalName);
        assertEquals("FeatureDetails", details);
    }

  


    @Test
    void testAddParameter() {
        // Arrange
        Parameter parameter1 = new Parameter();
        Parameter parameter2 = new Parameter();

        // Act
        feature.addParameter(parameter1);
        feature.addParameter(parameter2);

        // Assert
        List<Parameter> retrievedParameters = feature.getParameters();
        assertEquals(2, retrievedParameters.size());
        assertTrue(retrievedParameters.contains(parameter1));
        assertTrue(retrievedParameters.contains(parameter2));
    }

     @Test
    void testEquals() {
        // Arrange
        Feature feature1 = new Feature();
        feature1.setFeatureId(1L);
        Feature feature2 = new Feature();
        feature2.setFeatureId(1L);
        Feature feature3 = new Feature();
        feature3.setFeatureId(2L);

        // Act and Assert
        assertTrue(feature1.equals(feature2));
        assertFalse(feature1.equals(feature3));
    }

    @Test
    void testHashCode() {
        // Arrange
        Feature feature1 = new Feature();
        feature1.setFeatureId(1L);
        Feature feature2 = new Feature();
        feature2.setFeatureId(1L);
        Feature feature3 = new Feature();
        feature3.setFeatureId(2L);

        // Act and Assert
        assertEquals(feature1.hashCode(), feature2.hashCode());
        assertNotEquals(feature1.hashCode(), feature3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        feature.setFeatureId(1L);
        feature.setName("FeatureName");
        feature.setInternalName("InternalName");
        feature.setDetails("FeatureDetails");

        // Act
        String toString = feature.toString();

        // Assert
        assertTrue(toString.contains("FeatureName"));
        assertTrue(toString.contains("InternalName"));
        assertTrue(toString.contains("FeatureDetails"));
        assertTrue(toString.contains("1"));
    }

    @Test
    void testGetId() {
        // Arrange
        feature.setFeatureId(1L);

        // Act
        Long id = feature.getId();

        // Assert
        assertEquals(1L, id);
    }
    
}
