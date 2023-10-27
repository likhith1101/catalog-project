package com.spring.learningRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.learningRest.entity.Feature;
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

    public Parameter editParameter(Long parameterId, Parameter updatedParameter) {
        // Implement the logic to update a product by its ID here
        return parameterRepository.findById(parameterId)
                .map(existingParameter -> {
                    // Update the existing product with the new data
                    existingParameter.setName(updatedParameter.getName());
                    existingParameter.setInternalName(updatedParameter.getInternalName());
                    existingParameter.setDetails(updatedParameter.getDetails());
                    existingParameter.setParameterType(updatedParameter.getParameterType());
                     existingParameter.setValues(updatedParameter.getValues());
                    // existingProduct.setMaxProductsPerLocation(updatedProduct.getMaxProductsPerLocation());
                    // existingProduct.setPrice(updatedProduct.getPrice());
                    // existingProduct.setStock(updatedProduct.getStock());

                    // Save the updated product
                    return parameterRepository.save(existingParameter);
                })
                .orElse(null);
    }
    
    
   
}