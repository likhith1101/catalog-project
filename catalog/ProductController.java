package com.spring.learningRest.controller;




import com.spring.learningRest.entity.Cycle;
import com.spring.learningRest.entity.Product;
import com.spring.learningRest.repository.CycleRepository;
import com.spring.learningRest.repository.ProductRepository;
import com.spring.learningRest.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CycleRepository cycleRepository;

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/list")
    public List<Cycle> getAllCycleStocks() {
        return (List<Cycle>) cycleRepository.findAll();
    }

       @GetMapping("/prodlist")
    public List<Product> getAllProductStocks() {
        return (List<Product>) productRepository.findAll();
    }
      
      
    // Other CRUD endpoints
}
