package com.spring.learningRest;


import com.spring.learningRest.controller.ProductController;
import com.spring.learningRest.controller.exceptions.ResourceNotFoundException;
import com.spring.learningRest.entity.Product;
import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.repository.ProductRepository;
import com.spring.learningRest.repository.FeatureRepository;
import com.spring.learningRest.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    private ProductController productController;
    private ProductRepository productRepository;
    private ProductService productService;
    private FeatureRepository featureRepository;

    @BeforeEach
    public void setUp() {
        productRepository = mock(ProductRepository.class);
        productService = mock(ProductService.class);
        featureRepository = mock(FeatureRepository.class);

        productController = new ProductController(productRepository, productService, featureRepository);
    }

    @Test
    public void testGetAllProducts() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(productList);

        // Act
        List<Product> result = productController.getAllProducts();

        // Assert
        assertEquals(productList, result);
    }

    @Test
    public void testGetProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    public void testGetProductByIdNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddProduct() {
        // Arrange
        Product newProduct = new Product();
        Product savedProduct = new Product();
        when(productService.addProduct(newProduct)).thenReturn(savedProduct);

        // Act
        ResponseEntity<Product> response = productController.addProduct(newProduct);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedProduct, response.getBody());
    }

    @Test
    public void testAddProductError() {
        // Arrange
        Product newProduct = new Product();
        when(productService.addProduct(newProduct)).thenThrow(new IllegalArgumentException("Invalid product"));

        // Act
        ResponseEntity<Product> response = productController.addProduct(newProduct);

        // Assert
        assertEquals("Error", response.getBody().getName());
    }

    @Test
    public void testEditProduct() {
        // Arrange
        Long productId = 1L;
        Product updatedProduct = new Product();
        updatedProduct.setName("UpdatedProduct");

        Product existingProduct = new Product();
        when(productService.editProduct(eq(productId), any())).thenReturn(existingProduct);

        // Act
        ResponseEntity<Product> response = productController.editProduct(productId, updatedProduct);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingProduct, response.getBody());
    }

    @Test
    public void testEditProductNotFound() {
        // Arrange
        Long productId = 1L;
        Product updatedProduct = new Product();
        when(productService.editProduct(eq(productId), any())).thenReturn(null);

        // Act
        ResponseEntity<Product> response = productController.editProduct(productId, updatedProduct);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddFeatureToProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        Feature newFeature = new Feature();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(featureRepository.save(newFeature)).thenReturn(newFeature);

        // Act
        ResponseEntity<Product> response = productController.addFeatureToProduct(productId, newFeature);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
        assertEquals(1, product.getFeatures().size());
        assertEquals(newFeature, product.getFeatures().get(0));
    }

    @Test
    public void testAddFeatureToProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.addFeatureToProduct(productId, new Feature());

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetFeaturesByProductId() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        List<Feature> features = new ArrayList<>();
        features.add(new Feature());
        features.add(new Feature());

        product.setFeatures(features);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        List<Feature> result = productController.getFeaturesByProductId(productId);

        // Assert
        assertEquals(features, result);
    }

    @Test
    public void testGetFeaturesByProductIdNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        try {
            productController.getFeaturesByProductId(productId);
        } catch (ResourceNotFoundException ex) {
            assertEquals("Product not found with ID: 1", ex.getMessage());
        }
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<String> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product with ID 1 has been deleted.", response.getBody());
    }

    @Test
    public void testDeleteProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<String> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
