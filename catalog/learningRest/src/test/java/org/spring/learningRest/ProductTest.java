package org.spring.learningRest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.spring.learningRest.entity.Product;

class ProductTest {

    @Test
    void testProductConstructor() {
        Product product = new Product();
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getInternalName());
        assertNull(product.getDetails());
        assertEquals(0, product.getMaxProductsPerLocation());
    }

    @Test
    void testProductGettersAndSetters() {
        Product product = new Product();

        product.setId(1L);
        product.setName("Test Product");
        product.setInternalName("Internal Name");
        product.setDetails("Test Details");
        product.setMaxProductsPerLocation(10);

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("Internal Name", product.getInternalName());
        assertEquals("Test Details", product.getDetails());
        assertEquals(10, product.getMaxProductsPerLocation());
    }

    @Test
    void testHashCode() {
        Product product1 = new Product();
        Product product2 = new Product();

        assertEquals(product1.hashCode(), product2.hashCode());

        product1.setId(1L);
        assertNotEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testEquals() {
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();

        assertEquals(product1, product2);
        assertEquals(product2, product1);
        assertEquals(product1, product1);

        product1.setId(1L);
        assertNotEquals(product1, product2);
        assertNotEquals(product2, product1);
        assertNotEquals(product1, null);
        assertNotEquals(product1, "string");

        product2.setId(1L);
        assertEquals(product1, product2);

        product3.setId(2L);
        assertNotEquals(product1, product3);
    }

    // @Test
    // void testToString() {
    //     Product product = new Product();
    //     product.setId(1L);
    //     product.setName("Test Product");
    //     product.setInternalName("Internal Name");
    //     product.setDetails("Test Details");
    //     product.setMaxProductsPerLocation(10);

    //     String expectedToString = "Product{id=1, name='Test Product', internalName='Internal Name', details='Test Details', maxProductsPerLocation=10}";
    //     assertEquals(expectedToString, product.toString());
    // }
}
