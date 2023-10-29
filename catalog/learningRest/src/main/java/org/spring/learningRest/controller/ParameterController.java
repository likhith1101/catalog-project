package org.spring.learningRest.controller;

import java.util.List;

import org.spring.learningRest.entity.Feature;
import org.spring.learningRest.entity.Parameter;
import org.spring.learningRest.repository.FeatureRepository;
import org.spring.learningRest.repository.ParameterRepository;
import org.spring.learningRest.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/parameters")
public class ParameterController {
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private ParameterService parameterService;
    @Autowired
    private FeatureRepository featureRepository;



    @Autowired
    public ParameterController(ParameterRepository parameterRepository, ParameterService parameterService) {
        this.parameterRepository = parameterRepository;
        this.parameterService = parameterService;
    }


        @GetMapping("/list")
       
    public List<Parameter> getAllParameters() {
        // Implement logic to get all parameters
        return parameterRepository.findAll();
    }


    @PostMapping("/add")
   
    public ResponseEntity<Parameter> addParameter(@RequestBody Parameter newParameter) {
        try {
            Parameter parameter = parameterService.addParameter(newParameter);
            if (parameter != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(parameter);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (IllegalArgumentException ex) {
            // Provide an alternate response with a custom Feature object
            Parameter errorParameter = new Parameter();
            errorParameter.setName("Error name");
            errorParameter.setInternalName("Error internalName");
            errorParameter.setDetails("Error details");
            
          
            return ResponseEntity.ok(errorParameter);
        }
    }

    @PutMapping("/{parameterId}")
   
public ResponseEntity<Parameter> editParameter(@PathVariable Long parameterId, @RequestBody Parameter updatedParameter) {
    Parameter parameter = parameterService.editParameter(parameterId, updatedParameter);
    if (parameter != null) {
        return ResponseEntity.ok(parameter);
    } else {
        return ResponseEntity.notFound().build();
    }
}

@DeleteMapping("/{parameterId}")

public ResponseEntity<String> deleteParameter(@PathVariable Long parameterId) {
    // Check if the parameter exists
    if (parameterRepository.existsById(parameterId)) {
        // Get features using this parameter
        List<Feature> featuresUsingParameter = featureRepository.findByParametersParameterId(parameterId);

        // Disassociate the parameter from features
        featuresUsingParameter.forEach(feature -> feature.getParameters().removeIf(parameter -> parameter.getParameterId().equals(parameterId)));
        featureRepository.saveAll(featuresUsingParameter);

        // Now delete the parameter
        parameterRepository.deleteById(parameterId);
        return ResponseEntity.noContent().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}




}
