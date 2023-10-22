package com.spring.learningRest;

import com.spring.learningRest.controller.ProductController;
import com.spring.learningRest.entity.Product;
import com.spring.learningRest.repository.ProductRepository;
import com.spring.learningRest.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;



    @Test
    void testGetProductById() {
        // Mock a Product instance
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setName("Test Product");

        // When productRepository.findById() is called, return the mockProduct
        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(mockProduct));

        ResponseEntity<Product> response = productController.getProductById(1L);

        // Check if the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Check if the returned product matches the mockProduct
        assertEquals(mockProduct, response.getBody());
    }

    @Test
    void testGetProductByIdNotFound() {
        // When productRepository.findById() is called with an invalid ID, return null
        when(productRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(2L);

        // Check if the response status is NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddProduct() {
        // Mock a Product instance to be added
        Product mockProduct = new Product();
        mockProduct.setName("New Product");

        // When productService.addProduct() is called with any Product, return the mockProduct
        when(productService.addProduct(any(Product.class))).thenReturn(mockProduct);

        ResponseEntity<Product> response = productController.addProduct(mockProduct);

        // Check if the response status is CREATED
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // Check if the returned product matches the mockProduct
        assertEquals(mockProduct, response.getBody());
    }

    @Test
    void testAddProductError() {
        // Mock a scenario where adding a product results in an error
        when(productService.addProduct(any(Product.class))).thenThrow(new IllegalArgumentException("Invalid product"));

        ResponseEntity<Product> response = productController.addProduct(new Product());

        // Check if the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Check if the response contains an error product with the name "Error"
        assertEquals("Error", response.getBody().getName());
    }

    @Test
    void testEditProduct() {
        // Mock a Product instance to be updated
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");

        // When productService.editProduct() is called, return the updatedProduct
        when(productService.editProduct(1L, updatedProduct)).thenReturn(updatedProduct);

        ResponseEntity<Product> response = productController.editProduct(1L, updatedProduct);

        // Check if the response status is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Check if the returned product matches the updatedProduct
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    void testEditProductNotFound() {
        // When productService.editProduct() is called with an invalid ID, return null
        when(productService.editProduct(2L, new Product())).thenReturn(null);

        ResponseEntity<Product> response = productController.editProduct(2L, new Product());

        // Check if the response status is NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
