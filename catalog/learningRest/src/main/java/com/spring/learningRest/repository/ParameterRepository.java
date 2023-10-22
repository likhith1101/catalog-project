package com.spring.learningRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
    
}
