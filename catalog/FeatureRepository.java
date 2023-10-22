package com.spring.learningRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.learningRest.entity.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
}

