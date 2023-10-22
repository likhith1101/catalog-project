package com.spring.learningRest.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long featureId;
    private String name;
    private String internalName;
    private String details;
    public void setProduct(Product product) {
    }

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Parameter> parameters;


    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "parameterId")      // Use a different column name
    // private List<Parameter> parameters;

  
    public void addParameter(Parameter savedParameter) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        parameters.add(savedParameter);
    }


    public Long getId() {
        return featureId;
    }

    public void setId(Long featureId) {
        this.featureId = featureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Product getProduct() {
        return product;
    }

    


    // @ManyToOne
    // @JoinColumn(name = "product_id")
    // private Product product;

    //  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // private List<Parameter> parameters;
   

 
     

    // public void setProduct(Product product) {
    //     this.product = product;
    // }

    // public void setId(Long featureId) {
    //     this.featureId = featureId;
    // }



    // public void setId(Long featureId2) {
    // }

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id") // Use a different column name
    // private List<Parameter> parameters;
    // @ManyToOne
    // @JoinColumn(name = "product_id")
    // private Product product;

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "productId", referencedColumnName = "id")
    // private Product product;


    // Constructors, getters, and setters
}
