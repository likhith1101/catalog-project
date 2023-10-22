package com.spring.learningRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.learningRest.entity.Parameter;
import com.spring.learningRest.repository.ParameterRepository;
import com.spring.learningRest.repository.UserRepository;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public ParameterService(ParameterRepository ParameterRepository) {
        this.parameterRepository = parameterRepository;
    }

     public Parameter addParameter(Parameter newParameter) {
        if (newParameter == null) {
            throw new IllegalArgumentException("Invalid parameter data.");
        }

        // You can add more validation and business logic here, if necessary.

        return parameterRepository.save(newParameter);
    }

    
    

    
    

   
  

    
   
}

// @Service
// public class ParameterService {
//     @Autowired
//     private ParameterRepository parameterRepository;
//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     public ParameterService(parameterRepository parameterRepository) {
//         this.parameterRepository = parameterRepository;
//     }



   
    
   

   
    

//     // public Feature editFeature(Long featureId, Feature updatedFeature) {
//     //     // Implement the logic to update a product by its ID here
//     //     return featureRepository.findById(featureId)
//     //             .map(existingFeature -> {
//     //                 // Update the existing product with the new data
//     //                 existingFeature.setName(updatedFeature.getName());
//     //                 existingFeature.setInternalName(updatedFeature.getInternalName());
//     //                 existingFeature.setDetails(updatedFeature.getDetails());
//     //                 // existingProduct.setMaxProductsPerLocation(updatedProduct.getMaxProductsPerLocation());
//     //                 // existingProduct.setPrice(updatedProduct.getPrice());
//     //                 // existingProduct.setStock(updatedProduct.getStock());

//     //                 // Save the updated product
//     //                 return featureRepository.save(existingFeature);
//     //             })
//     //             .orElse(null);
//     // }
    

   

//     public Parameter addParameter(Parameter newParameter) {
//         if (newParameter == null) {
//             throw new IllegalArgumentException("Invalid parameter data.");
//         }

//         // You can add more validation and business logic here, if necessary.

//         return parameterRepository.save(newParameter);
//     }

// }