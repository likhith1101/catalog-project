package org.spring.learningRest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spring.learningRest.entity.Product;
import org.spring.learningRest.repository.ProductRepository;
import org.spring.learningRest.service.ProductService;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
   
    
   

// @Test
//    void testGetAllProducts() {
//        // Create a list of products for testing
//        List<Product> products = new ArrayList<>();
//        products.add(new Product(1L, "Product 1", "Internal 1", "Details 1", 10));
//        products.add(new Product(2L, "Product 2", "Internal 2", "Details 2", 5));
//
//        when(productRepository.findAll()).thenReturn(products);
//
//        List<Product> result = productService.getAllProducts();
//
//        // Assert that the result from the service is the same as the one we provided
//        assertEquals(products, result);
//    }

    

    @Test
    void testEditProduct() {
        // Create a sample product for testing
    	Product sampleProduct = new Product();
    	sampleProduct.setId(1L);
    	sampleProduct.setName("Sample Product");
    	sampleProduct.setInternalName("Internal Sample");
    	sampleProduct.setDetails("Sample Details");
    	sampleProduct.setMaxProductsPerLocation(20);

        // Mock the repository behavior
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));
        when(productRepository.save(sampleProduct)).thenReturn(sampleProduct);

//        Product updatedProduct = new Product(1L, "Updated Product", "Internal Updated", "Updated Details", 30);
        Product updatedProduct = new Product();
    	updatedProduct.setId(1L);
    	updatedProduct.setName("Updated Product");
    	updatedProduct.setInternalName("Internal Updated");
    	updatedProduct.setDetails("Updated Details");
    	updatedProduct.setMaxProductsPerLocation(30);
        Product result = productService.editProduct(1L, updatedProduct);

        // Assert that the result is the same as the updated product
        assertEquals(updatedProduct, result);
    }

    @Test
    void testAddProduct() {
        // Create a sample product for testing
    	Product sampleProduct = new Product();
    	sampleProduct.setId(1L);
    	sampleProduct.setName("Sample Product");
    	sampleProduct.setInternalName("Internal Sample");
    	sampleProduct.setDetails("Sample Details");
    	sampleProduct.setMaxProductsPerLocation(20);

        // Mock the repository behavior
        when(productRepository.save(sampleProduct)).thenReturn(sampleProduct);

        Product result = productService.addProduct(sampleProduct);

        // Assert that the result is the same as the sample product
        assertEquals(sampleProduct, result);
    }
}