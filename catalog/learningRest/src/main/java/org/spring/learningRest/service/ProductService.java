package org.spring.learningRest.service;



import org.spring.learningRest.entity.Product;
import org.spring.learningRest.repository.FeatureRepository;
import org.spring.learningRest.repository.ProductRepository;
import org.spring.learningRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FeatureService featureService;
    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    

   
    
   

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
    
    

    public Product editProduct(Long productId, Product updatedProduct) {
        // Implement the logic to update a product by its ID here
        return productRepository.findById(productId)
                .map(existingProduct -> {
                    // Update the existing product with the new data
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setInternalName(updatedProduct.getInternalName());
                    existingProduct.setDetails(updatedProduct.getDetails());
                    existingProduct.setMaxProductsPerLocation(updatedProduct.getMaxProductsPerLocation());
                    
                    return productRepository.save(existingProduct);
                })
                .orElse(null);
    }
    

    
    
    public Product addProduct(Product newProduct) {
        // Perform validation or any other business logic as needed
        if (newProduct == null) {
            throw new IllegalArgumentException("Invalid product data.");
        }

        // You can add more validation and business logic here, such as setting default values,
        // generating unique identifiers, or performing other data-related tasks.

        // Save the new product to the repository
        return productRepository.save(newProduct);
    }

   

   
  
}
