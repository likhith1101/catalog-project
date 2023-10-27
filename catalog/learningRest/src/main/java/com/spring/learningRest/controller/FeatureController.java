package com.spring.learningRest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.learningRest.controller.exceptions.ResourceNotFoundException;
import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Parameter;
import com.spring.learningRest.entity.Product;
import com.spring.learningRest.repository.FeatureRepository;
import com.spring.learningRest.repository.ParameterRepository;
import com.spring.learningRest.service.FeatureService;

import java.util.List;

@RestController
@RequestMapping("/api/features")
public class FeatureController {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private FeatureService featureService;

    @Autowired
    public FeatureController(FeatureRepository featureRepository, FeatureService featureService) {
        this.featureRepository = featureRepository;
        this.featureService = featureService;
    }

    // @Autowired
    // private ParameterRepository parameterRepository;

    
    // @PostMapping("/add")
    // public Feature addFeature(@RequestBody Feature feature) {
    //     // Implement logic to add a feature
    //       return (List<Feature>) featureRepository.findAll();
      
    // }

    @GetMapping("/list")
    public List<Feature> getAllFeatures() {
        // Implement logic to get all features
        return featureRepository.findAll();
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Long featureId) {
        // Implement logic to get a feature by ID
        Feature feature = featureRepository.findById(featureId).orElse(null);
        if (feature != null) {
            return ResponseEntity.ok(feature);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping("/add/{featureId}")
//public ResponseEntity<Feature> addFeatureWithId(
//        @PathVariable Long featureId,
//        @RequestBody Feature newFeature) {
//    try {
//        Feature feature = featureService.addFeatureWithId(featureId, newFeature);
//        if (feature != null) {
//            return ResponseEntity.status(HttpStatus.CREATED).body(feature);
//        } else {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    } catch (IllegalArgumentException ex) {
//        // Provide an alternate response with a custom Product object
//        Feature errorFeature = new Feature();
//        errorFeature.setName("Error");
//        return ResponseEntity.ok(errorFeature); // This doesn't explicitly set HTTP status to bad request
//    }
//}

@PostMapping("/{featureId}/add-parameter")
    public ResponseEntity<Feature> addParameterToFeature(
            @PathVariable Long featureId,
            @RequestBody Parameter newParameter) {
        Optional<Feature> optionalFeature = featureRepository.findById(featureId);
        

        if (optionalFeature.isPresent()) {
            Feature feature = optionalFeature.get();

            // Add the new parameter to the feature
            feature.addParameter(newParameter);

            // Save the feature to persist the changes
            featureRepository.save(feature);

            return ResponseEntity.status(HttpStatus.CREATED).body(feature);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

@PostMapping("/add")
    public ResponseEntity<Feature> addFeature(@RequestBody Feature newFeature) {
        try {
            Feature feature = featureService.addFeature(newFeature);
            if (feature != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(feature);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (IllegalArgumentException ex) {
            // Provide an alternate response with a custom Feature object
            Feature errorFeature = new Feature();
            errorFeature.setName("Error");
           // errorFeature.setDescription("Error while adding the feature: " + ex.getMessage());
            return ResponseEntity.ok(errorFeature);
        }
    }

@PutMapping("/{featureId}")
public ResponseEntity<Feature> editFeature(@PathVariable Long featureId, @RequestBody Feature updatedFeature) {
    Feature feature = featureService.editFeature(featureId, updatedFeature);
    if (feature != null) {
        return ResponseEntity.ok(feature);
    } else {
        return ResponseEntity.notFound().build();
    }
}



    @DeleteMapping("/{featureId}")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long featureId) {
        // Implement logic to delete a feature
        Feature feature = featureRepository.findById(featureId).orElse(null);
        if (feature != null) {
            // Implement the delete logic here
            featureRepository.delete(feature);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/{featureId}/parameters")
    public List<Parameter> getParametersByFeatureId(@PathVariable Long featureId) {
        // First, retrieve the feature by its ID
        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found with ID: " + featureId));

        // Now, get the list of parameters associated with the feature
        List<Parameter> parameters = feature.getParameters();

        return parameters;
    }
}

