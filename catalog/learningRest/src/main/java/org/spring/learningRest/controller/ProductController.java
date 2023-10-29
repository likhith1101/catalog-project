package org.spring.learningRest.controller;

import org.spring.learningRest.controller.exceptions.ResourceNotFoundException;
import org.spring.learningRest.entity.Feature;
import org.spring.learningRest.entity.Product;
import org.spring.learningRest.repository.FeatureRepository;
import org.spring.learningRest.repository.ProductRepository;
import org.spring.learningRest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, FeatureRepository featureRepository) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.featureRepository = featureRepository;
    }


    @GetMapping("/list")
  
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    
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
        Product errorProduct = new Product();
        errorProduct.setName("Error");
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
            
            newFeature.setProduct(product);
            Feature savedFeature = featureRepository.save(newFeature);

            product.addFeature(savedFeature);
            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @GetMapping("/{productId}/features")
  
    public List<Feature> getFeaturesByProductId(@PathVariable Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

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
