package com.spring.learningRest.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.repository.FeatureRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;



@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String internalName;
    private String details;
    // private String someProperty;

    @Column(name = "max_products_per_location")
   private int maxProductsPerLocation;

     @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id") // Use a different column name
    private List<Feature> features;


   public void addFeature(Feature savedFeature) {
    if (features == null) {
        features = new ArrayList<>();
    }
    features.add(savedFeature);
}


  




   
   



  

     

    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private List<Feature> features = new ArrayList<>();

    // public void addFeature(Feature feature) {
    //     this.features.add(feature);
    //     feature.setProduct(this); // Set the product for the feature
    // }

    

   
   
//    
//    public Product(long id, String name, String internalName, String details, int maxProductsPerLocation) {
//        this.id = id;
//        this.name = name;
//        this.internalName = internalName;
//        this.details = details;
//        this.maxProductsPerLocation = maxProductsPerLocation;
//    }

    // public String getSomeProperty() {
    //     return someProperty;
    // }
    


    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "product_id") // Use a different column name
    // private List<Feature> features;

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "par_id") // Use a different column name
    // private List<Parameter> parameters;

    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "prod_id") // Use a different column name
    // private List<Product> products;


    


  
   

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "productId", referencedColumnName = "id")
    // private Feature feature;

    //  @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "product", referencedColumnName = "id")
    // private  List<Feature> features;

    // Constructors, getters, and setters
//    public Product(long id, String name, String internalName, String details, int maxProductsPerLocation) {
//        this.id = id;
//        this.name = name;
//        this.internalName=internalName;
//        this.details = details;
//        this.maxProductsPerLocation = maxProductsPerLocation;
//    }
}
