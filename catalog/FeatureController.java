package com.spring.learningRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Product;
import com.spring.learningRest.repository.FeatureRepository;

@RestController
@RequestMapping("/api/features")
public class FeatureController {
    @Autowired
    private FeatureRepository featureRepository;
   

  

        @GetMapping("/featlist")
    public List<Feature> getAllStocks() {
        return (List<Feature>) featureRepository.findAll();
    }
   
}

