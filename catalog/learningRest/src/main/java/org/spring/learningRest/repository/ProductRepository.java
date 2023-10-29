package org.spring.learningRest.repository;

import org.spring.learningRest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
 
}
