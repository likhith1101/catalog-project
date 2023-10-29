package org.spring.learningRest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spring.learningRest.entity.Parameter;
import org.spring.learningRest.entity.ParameterType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParameterTest {
    private Parameter parameter;

    @BeforeEach
    public void setUp() {
        parameter = new Parameter();
    }

    @Test
    void testParameterId() {
        // Test setting and getting the parameterId
        Long expectedId = 1L;
        parameter.setParameterId(expectedId);
        Long actualId = parameter.getParameterId();
        assertEquals(expectedId, actualId);
    }

    @Test
    void testName() {
        // Test setting and getting the name
        String expectedName = "Sample Parameter";
        parameter.setName(expectedName);
        String actualName = parameter.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    void testInternalName() {
        // Test setting and getting the internalName
        String expectedInternalName = "Sample Internal Name";
        parameter.setInternalName(expectedInternalName);
        String actualInternalName = parameter.getInternalName();
        assertEquals(expectedInternalName, actualInternalName);
    }

    @Test
    void testDetails() {
        // Test setting and getting the details
        String expectedDetails = "Sample Details";
        parameter.setDetails(expectedDetails);
        String actualDetails = parameter.getDetails();
        assertEquals(expectedDetails, actualDetails);
    }

    @Test
    void testParameterType() {
        // Test setting and getting the parameterType
        ParameterType expectedType = ParameterType.QUANTITY;
        parameter.setParameterType(expectedType);
        ParameterType actualType = parameter.getParameterType();
        assertEquals(expectedType, actualType);
    }

    @Test
    void testDefaultParameterId() {
        // Ensure that the parameterId is null by default
        assertNull(parameter.getParameterId());
    }

     @Test
    void testHashCode() {
        Parameter parameter1 = new Parameter();
        Parameter parameter2 = new Parameter();

        assertEquals(parameter1.hashCode(), parameter2.hashCode());

        parameter1.setParameterId(1L);
        assertNotEquals(parameter1.hashCode(), parameter2.hashCode());
    }

    @Test
    void testEquals() {
        Parameter parameter1 = new Parameter();
        Parameter parameter2 = new Parameter();
        Parameter parameter3 = new Parameter();

        assertEquals(parameter1, parameter2);
        assertEquals(parameter2, parameter1);
        assertEquals(parameter1, parameter1);

        parameter1.setParameterId(1L);
        assertNotEquals(parameter1, parameter2);
        assertNotEquals(parameter2, parameter1);
        assertNotEquals(parameter1, null);
        assertNotEquals(parameter1, "string");

        parameter2.setParameterId(1L);
        assertEquals(parameter1, parameter2);

        parameter3.setParameterId(2L);
        assertNotEquals(parameter1, parameter3);
    }

    // @Test
    // void testToString() {
    //     Parameter parameter = new Parameter();
    //     parameter.setParameterId(1L);
    //     parameter.setName("Sample Parameter");
    //     parameter.setInternalName("Sample Internal Name");
    //     parameter.setDetails("Sample Details");
    //     parameter.setParameterType(ParameterType.QUANTITY);

    //     String expectedToString = "Parameter{parameterId=1, name='Sample Parameter', internalName='Sample Internal Name', details='Sample Details', parameterType=QUANTITY}";
    //     assertEquals(expectedToString, parameter.toString());
    // }
}

