package org.spring.learningRest.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;





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
    

    @Column(name = "max_products_per_location")
   private int maxProductsPerLocation;

     @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id") 
    private List<Feature> features;


   public void addFeature(Feature savedFeature) {
    if (features == null) {
        features = new ArrayList<>();
    }
    features.add(savedFeature);
}

}
