package org.spring.learningRest.entity;







import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private ParameterType parameterType;
    private String values;
   

   

   

    public void setId(Long parameterId) {
        this.parameterId = parameterId;
    }

    

    public Parameter() {
    }

    public Parameter(Long parameterId, String name) {
        this.parameterId = parameterId;
        this.name = name;
    }
    
  
    

}