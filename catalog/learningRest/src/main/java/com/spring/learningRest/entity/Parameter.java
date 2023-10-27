package com.spring.learningRest.entity;







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
    private ParameterType parameterType;// Enum for "quantity," "price," "other"
    private String values;
   

    // @ManyToOne
    // @JoinColumn(name = "feat_id")
    // private Feature feature;

   

    public void setId(Long parameterId) {
        this.parameterId = parameterId;
    }

    
    // Default constructor
    public Parameter() {
    }

    // Constructor with id and name
    public Parameter(Long parameterId, String name) {
        this.parameterId = parameterId;
        this.name = name;
    }
    
  
    
    // @ManyToOne
    // @JoinColumn(name = "feature_id")
    // private Feature feature;

    // Constructors, getters, and setters
}