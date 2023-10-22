package com.spring.learningRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.repository.FeatureRepository;
import com.spring.learningRest.repository.UserRepository;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }



   
    
   

   
    

    public Feature editFeature(Long featureId, Feature updatedFeature) {
        // Implement the logic to update a product by its ID here
        return featureRepository.findById(featureId)
                .map(existingFeature -> {
                    // Update the existing product with the new data
                    existingFeature.setName(updatedFeature.getName());
                    existingFeature.setInternalName(updatedFeature.getInternalName());
                    existingFeature.setDetails(updatedFeature.getDetails());
                    // existingProduct.setMaxProductsPerLocation(updatedProduct.getMaxProductsPerLocation());
                    // existingProduct.setPrice(updatedProduct.getPrice());
                    // existingProduct.setStock(updatedProduct.getStock());

                    // Save the updated product
                    return featureRepository.save(existingFeature);
                })
                .orElse(null);
    }
    

   

    public Feature addFeature(Feature newFeature) {
        if (newFeature == null) {
            throw new IllegalArgumentException("Invalid feature data.");
        }

        // You can add more validation and business logic here, if necessary.

        return featureRepository.save(newFeature);
    }

    
    
}

