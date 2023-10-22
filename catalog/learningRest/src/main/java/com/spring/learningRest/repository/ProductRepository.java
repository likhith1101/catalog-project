package com.spring.learningRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.learningRest.entity.Feature;
import com.spring.learningRest.entity.Product;
import com.spring.learningRest.entity.User;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
 
}
