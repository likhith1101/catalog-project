package com.spring.learningRest;

import com.dto.FeatureSummary;
import com.dto.ParameterSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FeatureSummaryTest {

    private FeatureSummary featureSummary;

    @BeforeEach
    public void setUp() {
        featureSummary = new FeatureSummary();
    }

    @Test
    public void testFeatureId() {
        // Test setting and getting the featureId
        Long expectedFeatureId = 1L;
        featureSummary.setFeatureId(expectedFeatureId);
        Long actualFeatureId = featureSummary.getFeatureId();
        assertEquals(expectedFeatureId, actualFeatureId);
    }

    @Test
    public void testName() {
        // Test setting and getting the name
        String expectedName = "Sample Feature";
        featureSummary.setName(expectedName);
        String actualName = featureSummary.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void testInternalName() {
        // Test setting and getting the internalName
        String expectedInternalName = "Sample Internal Name";
        featureSummary.setInternalName(expectedInternalName);
        String actualInternalName = featureSummary.getInternalName();
        assertEquals(expectedInternalName, actualInternalName);
    }

    @Test
    public void testDetails() {
        // Test setting and getting the details
        String expectedDetails = "Sample Details";
        featureSummary.setDetails(expectedDetails);
        String actualDetails = featureSummary.getDetails();
        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    public void testParameters() {
        // Test setting and getting the parameters
        List<ParameterSummary> expectedParameters = new ArrayList<>();
        ParameterSummary parameter1 = new ParameterSummary();
        parameter1.setParameterId(1L);
        parameter1.setName("Parameter 1");
        expectedParameters.add(parameter1);
        ParameterSummary parameter2 = new ParameterSummary();
        parameter2.setParameterId(2L);
        parameter2.setName("Parameter 2");
        expectedParameters.add(parameter2);

        featureSummary.setParameters(expectedParameters);
        List<ParameterSummary> actualParameters = featureSummary.getParameters();
        assertEquals(expectedParameters, actualParameters);
    }
}

