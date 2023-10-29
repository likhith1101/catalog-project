package org.spring.learningRest.controller;

import java.util.List;
import java.util.Optional;

import org.spring.learningRest.controller.exceptions.ResourceNotFoundException;
import org.spring.learningRest.entity.Feature;
import org.spring.learningRest.entity.Parameter;
import org.spring.learningRest.repository.FeatureRepository;
import org.spring.learningRest.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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

    

    @GetMapping("/list")
 
    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    @GetMapping("/{featureId}")
   
    public ResponseEntity<Feature> getFeatureById(@PathVariable Long featureId) {
        Feature feature = featureRepository.findById(featureId).orElse(null);
        if (feature != null) {
            return ResponseEntity.ok(feature);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

@PostMapping("/{featureId}/add-parameter")

    public ResponseEntity<Feature> addParameterToFeature(
            @PathVariable Long featureId,
            @RequestBody Parameter newParameter) {
        Optional<Feature> optionalFeature = featureRepository.findById(featureId);
        

        if (optionalFeature.isPresent()) {
            Feature feature = optionalFeature.get();

           
            feature.addParameter(newParameter);

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
            
            Feature errorFeature = new Feature();
            errorFeature.setName("Error");
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
        Feature feature = featureRepository.findById(featureId).orElse(null);
        if (feature != null) {
            featureRepository.delete(feature);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/{featureId}/parameters")
   
    public List<Parameter> getParametersByFeatureId(@PathVariable Long featureId) {

        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found with ID: " + featureId));

        List<Parameter> parameters = feature.getParameters();

        return parameters;
    }
}

