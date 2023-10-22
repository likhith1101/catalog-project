package com.spring.learningRest;

import org.junit.jupiter.api.Test;

import com.dto.FeatureSummary;
import com.dto.ProductSummary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class ProductSummaryTest {

    @Test
    public void testSetProductId() {
        // Arrange
        ProductSummary productSummary = new ProductSummary();
        Long productId = 1L;

        // Act
        productSummary.setProductId(productId);

        // Assert
        assertEquals(productId, productSummary.getProductId());
    }

    @Test
    public void testSetName() {
        // Arrange
        ProductSummary productSummary = new ProductSummary();
        String name = "Test Product";

        // Act
        productSummary.setName(name);

        // Assert
        assertEquals(name, productSummary.getName());
    }

    @Test
    public void testSetDetails() {
        // Arrange
        ProductSummary productSummary = new ProductSummary();
        String details = "Test Details";

        // Act
        productSummary.setDetails(details);

        // Assert
        assertEquals(details, productSummary.getDetails());
    }

    @Test
    public void testSetFeatures() {
        // Arrange
        ProductSummary productSummary = new ProductSummary();
        List<FeatureSummary> features = new ArrayList<>();

        // Act
        productSummary.setFeatures(features);

        // Assert
        assertEquals(features, productSummary.getFeatures());
    }
}
