package org.spring.learningRest.repository;



import java.util.List;

import org.spring.learningRest.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByParametersParameterId(Long parameterId);

}
