package com.spring.learningRest.controller;







import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.learningRest.controller.exceptions.ResourceNotFoundException;
import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Product;
import com.spring.learningRest.repository.FeatureRepository;
import com.spring.learningRest.repository.ProductRepository;
import com.spring.learningRest.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private FeatureRepository featureRepository;



    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // @GetMapping("/list")
    // public List<Product> getAllProducts() {
    //     // Implement logic to get all products
    //     return (List<Product>) productRepository.findAll();
    // }
    





    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productRepository.findById(productId)
                .orElse(null);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



@PostMapping("/add")
public ResponseEntity<Product> addProduct(
        @RequestBody Product newProduct) {
    try {
        Product product = productService.addProduct(newProduct);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } catch (IllegalArgumentException ex) {
        // Provide an alternate response with a custom Product object
        Product errorProduct = new Product();
        errorProduct.setName("Error");
        // errorProduct.setDescription("Error while adding the product: " + ex.getMessage());
        return ResponseEntity.ok(errorProduct);
    }
}


    

    @PutMapping("/{productId}")
    public ResponseEntity<Product> editProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        Product product = productService.editProduct(productId, updatedProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{productId}/add-feature")
    public ResponseEntity<Product> addFeatureToProduct(
            @PathVariable Long productId,
            @RequestBody Feature newFeature) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            
            // Associate the new feature with the product
            newFeature.setProduct(product);
            Feature savedFeature = featureRepository.save(newFeature);

            // Add the feature to the product's list of features
            product.addFeature(savedFeature);
            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @GetMapping("/{productId}/features")
    public List<Feature> getFeaturesByProductId(@PathVariable Long productId) {
        // First, retrieve the product by its ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        // Now, get the list of features associated with the product
        List<Feature> features = product.getFeatures();

        return features;
    }



    @DeleteMapping("/{productId}")
public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
    Optional<Product> optionalProduct = productRepository.findById(productId);

    if (optionalProduct.isPresent()) {
        Product product = optionalProduct.get();
        productRepository.delete(product);
        return ResponseEntity.ok("Product with ID " + productId + " has been deleted.");
    } else {
        return ResponseEntity.notFound().build();
    }
}





   
  
    
}
