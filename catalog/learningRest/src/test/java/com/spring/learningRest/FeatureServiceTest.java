// package com.spring.learningRest;

// import com.spring.learningRest.entity.Feature;
// import com.spring.learningRest.entity.Product;
// import com.spring.learningRest.repository.FeatureRepository;
// import com.spring.learningRest.repository.ProductRepository;
// import com.spring.learningRest.service.FeatureService;
// import com.spring.learningRest.service.ProductService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// @SpringBootTest
// public class FeatureServiceTest {

//     @InjectMocks
//     private FeatureService featureService;

//     @Mock
//     private FeatureRepository featureRepository;

//     @BeforeEach
//     public void init() {
//         MockitoAnnotations.openMocks(this);
//     }
   
    
   

// // @Test
// //    public void testGetAllProducts() {
// //        // Create a list of products for testing
// //        List<Product> products = new ArrayList<>();
// //        products.add(new Product(1L, "Product 1", "Internal 1", "Details 1", 10));
// //        products.add(new Product(2L, "Product 2", "Internal 2", "Details 2", 5));
// //
// //        when(productRepository.findAll()).thenReturn(products);
// //
// //        List<Product> result = productService.getAllProducts();
// //
// //        // Assert that the result from the service is the same as the one we provided
// //        assertEquals(products, result);
// //    }

    

//     @Test
//     public void testEditFeature() {
//         // Create a sample product for testing
//     	Feature sampleFeature = new Feature();
//     	sampleFeature.setId(1L);
//     	sampleFeature.setName("Sample Product");
//     	sampleFeature.setInternalName("Internal Sample");
//     	sampleFeature.setDetails("Sample Details");
    	
//         // Mock the repository behavior
//         when(featureRepository.findById(1L)).thenReturn(Optional.of(sampleFeature));
//         when(featureRepository.save(sampleFeature)).thenReturn(sampleFeature);

// //        Product updatedProduct = new Product(1L, "Updated Product", "Internal Updated", "Updated Details", 30);
//         Feature updatedFeature = new Feature();
//     	updatedFeature.setId(1L);
//     	updatedFeature.setName("Updated Product");
//     	updatedFeature.setInternalName("Internal Updated");
//     	updatedFeature.setDetails("Updated Details");
 
//         Feature result = featureService.editFeature(1L, updatedFeature);

//         // Assert that the result is the same as the updated product
//         assertEquals(updatedFeature, result);
//     }

//     @Test
//     public void testAddFeature() {
//         // Create a sample product for testing
//     	Feature sampleFeature = new Feature();
//     	sampleFeature.setId(1L);
//     	sampleFeature.setName("Sample Product");
//     	sampleFeature.setInternalName("Internal Sample");
//     	sampleFeature.setDetails("Sample Details");
    	
//         // Mock the repository behavior
//         when(featureRepository.save(sampleFeature)).thenReturn(sampleFeature);

//         Feature result = featureService.addFeature(sampleFeature);

//         // Assert that the result is the same as the sample product
//         assertEquals(sampleFeature, result);
//     }
// }

