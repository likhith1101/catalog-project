package com.spring.learningRest;

import com.spring.learningRest.entity.ParameterType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterTypeTest {

    @Test
    public void testEnumValues() {
        // Verify that the enum values are defined correctly
        assertEquals("QUANTITY", ParameterType.QUANTITY.name());
        assertEquals("PRICE", ParameterType.PRICE.name());
        assertEquals("OTHER", ParameterType.OTHER.name());
    }

    @Test
    public void testEnumValuesCount() {
        // Verify the count of enum values
        assertEquals(3, ParameterType.values().length);
    }

    @Test
    public void testEnumValueOf() {
        // Test using valueOf to get enum values
        assertEquals(ParameterType.QUANTITY, ParameterType.valueOf("QUANTITY"));
        assertEquals(ParameterType.PRICE, ParameterType.valueOf("PRICE"));
        assertEquals(ParameterType.OTHER, ParameterType.valueOf("OTHER"));
    }

    @Test
    public void testEquality() {
        // Verify equality and inequality of enum values
        assertTrue(ParameterType.QUANTITY == ParameterType.QUANTITY);
        assertTrue(ParameterType.PRICE == ParameterType.PRICE);
        assertTrue(ParameterType.OTHER == ParameterType.OTHER);
        assertTrue(ParameterType.QUANTITY != ParameterType.PRICE);
        assertTrue(ParameterType.PRICE != ParameterType.OTHER);
    }
}

