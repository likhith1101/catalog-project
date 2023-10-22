package com.spring.learningRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Parameter;
import com.spring.learningRest.repository.ParameterRepository;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {
    @Autowired
    private ParameterRepository parameterRepository;

  

    @GetMapping("/parlist")
    public List<Parameter> getAllParameters() {
       return (List<Parameter>) parameterRepository.findAll();
    }

    
}
