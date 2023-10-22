package com.spring.learningRest.entity;



import org.hibernate.usertype.DynamicParameterizedType.ParameterType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parameterId;
    private String name;
    private String internalName;
    private String details;
    private ParameterType parameterType; // Enum for "quantity," "price," "other"
    
    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;

    // Constructors, getters, and setters
}
