package org.spring.learningRest.service;

import org.spring.learningRest.entity.Parameter;
import org.spring.learningRest.repository.ParameterRepository;
import org.spring.learningRest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
public ParameterService(ParameterRepository parameterRepository) {
    this.parameterRepository = parameterRepository;
}

     public Parameter addParameter(Parameter newParameter) {
        if (newParameter == null) {
            throw new IllegalArgumentException("Invalid parameter data.");
        }


        return parameterRepository.save(newParameter);
    }

    public Parameter editParameter(Long parameterId, Parameter updatedParameter) {
       
        return parameterRepository.findById(parameterId)
                .map(existingParameter -> {
                    existingParameter.setName(updatedParameter.getName());
                    existingParameter.setInternalName(updatedParameter.getInternalName());
                    existingParameter.setDetails(updatedParameter.getDetails());
                    existingParameter.setParameterType(updatedParameter.getParameterType());
                     existingParameter.setValues(updatedParameter.getValues());
            
                    return parameterRepository.save(existingParameter);
                })
                .orElse(null);
    }
    
    
   
}