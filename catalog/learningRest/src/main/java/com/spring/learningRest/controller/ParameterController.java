package com.spring.learningRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Parameter;
import com.spring.learningRest.repository.ParameterRepository;
import com.spring.learningRest.service.ParameterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController
// @RequestMapping("/api/parameters")
// public class ParameterController {
//     @Autowired
//     private ParameterRepository parameterRepository;
//     @Autowired
//     private ParameterService parameterService;

//     // @PostMapping("/add")
//     // public Parameter addParameter(@RequestBody Parameter parameter) {
//     //     // Implement logic to add a parameter
//     //     return parameterRepository.save(parameter);
//     // }

//     @GetMapping("/list")
//     public List<Parameter> getAllParameters() {
//         // Implement logic to get all parameters
//         return parameterRepository.findAll();
//     }

//     @GetMapping("/{parameterId}")
//     public ResponseEntity<Parameter> getParameterById(@PathVariable Long parameterId) {
//         // Implement logic to get a parameter by ID
//         Parameter parameter = parameterRepository.findById(parameterId).orElse(null);
//         if (parameter != null) {
//             return ResponseEntity.ok(parameter);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

    
// @PostMapping("/add")
//     public ResponseEntity<Parameter> addParameter(@RequestBody Parameter newParameter) {
//         try {
//             Parameter parameter = parameterService.addParameter(newParameter);
//             if (parameter != null) {
//                 return ResponseEntity.status(HttpStatus.CREATED).body(parameter);
//             } else {
//                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//             }
//         } catch (IllegalArgumentException ex) {
//             // Provide an alternate response with a custom Parameter object
//             Parameter errorParameter = new Parameter();
//             errorParameter.setName("Error");
//           //  errorParameter.setDescription("Error while adding the parameter: " + ex.getMessage());
//             return ResponseEntity.ok(errorParameter);
//         }
//     }

// @PutMapping("/{parameterId}")
// public ResponseEntity<Parameter> editParameter(@PathVariable Long parameterId, @RequestBody Parameter updatedParameter) {
//     Parameter parameter = parameterService.editParameter(parameterId, updatedParameter);
//     if (parameter != null) {
//         return ResponseEntity.ok(parameter);
//     } else {
//         return ResponseEntity.notFound().build();
//     }
// }


//     @DeleteMapping("/{parameterId}")
//     public ResponseEntity<Void> deleteParameter(@PathVariable Long parameterId) {
//         // Implement logic to delete a parameter
//         Parameter parameter = parameterRepository.findById(parameterId).orElse(null);
//         if (parameter != null) {
//             // Implement the delete logic here
//             parameterRepository.delete(parameter);
//             return ResponseEntity.noContent().build();
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }
// }



@RestController
@RequestMapping("/api/parameters")
public class ParameterController {
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private ParameterService parameterService;


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
            errorParameter.setName("Error");
            errorParameter.setInternalName("Error");
            errorParameter.setDetails("Error");
            
            
           // errorFeature.setDescription("Error while adding the feature: " + ex.getMessage());
            return ResponseEntity.ok(errorParameter);
        }
    }

}