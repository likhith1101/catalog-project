package com.spring.learningRest;

import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FeatureTest {
    private Feature feature;

    @BeforeEach
    public void setUp() {
        feature = new Feature();
    }

    @Test
    public void testFeatureId() {
        // Test setting and getting the featureId
        Long expectedId = 1L;
        feature.setFeatureId(expectedId);
        Long actualId = feature.getFeatureId();
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testName() {
        // Test setting and getting the name
        String expectedName = "Sample Feature";
        feature.setName(expectedName);
        String actualName = feature.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testInternalName() {
        // Test setting and getting the internalName
        String expectedInternalName = "Sample Internal Name";
        feature.setInternalName(expectedInternalName);
        String actualInternalName = feature.getInternalName();
        assertEquals(expectedInternalName, actualInternalName);
    }

    @Test
    public void testDetails() {
        // Test setting and getting the details
        String expectedDetails = "Sample Details";
        feature.setDetails(expectedDetails);
        String actualDetails = feature.getDetails();
        assertEquals(expectedDetails, actualDetails);
    }

   

     @Test
    public void testSetProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Sample Product");

        feature.setProduct(product);

        assertEquals(product, feature.getProduct());
    }

    @Test
    public void testHashCode() {
        Feature feature1 = new Feature();
        Feature feature2 = new Feature();

        assertEquals(feature1.hashCode(), feature2.hashCode());

        feature1.setFeatureId(1L);
        assertNotEquals(feature1.hashCode(), feature2.hashCode());
    }

    @Test
    public void testEquals() {
        Feature feature1 = new Feature();
        Feature feature2 = new Feature();
        Feature feature3 = new Feature();

        assertEquals(feature1, feature2);
        assertEquals(feature2, feature1);
        assertEquals(feature1, feature1);

        feature1.setFeatureId(1L);
        assertNotEquals(feature1, feature2);
        assertNotEquals(feature2, feature1);
        assertNotEquals(feature1, null);
        assertNotEquals(feature1, "string");

        feature2.setFeatureId(1L);
        assertEquals(feature1, feature2);

        feature3.setFeatureId(2L);
        assertNotEquals(feature1, feature3);
    }

    // @Test
    // public void testToString() {
    //     Product product = new Product();
    //     product.setId(1L);
    //     product.setName("Sample Product");

    //     feature.setFeatureId(1L);
    //     feature.setName("Sample Feature");
    //     feature.setInternalName("Sample Internal Name");
    //     feature.setDetails("Sample Details");
    //     feature.setProduct(product);

    //     String expectedToString = "Feature{featureId=1, name='Sample Feature', internalName='Sample Internal Name', details='Sample Details', product=" + product + "}";
    //     assertEquals(expectedToString, feature.toString());
    // }

    @Test
    public void testDefaultFeatureId() {
        // Ensure that the featureId is null by default
        assertNull(feature.getFeatureId());
    }
}

