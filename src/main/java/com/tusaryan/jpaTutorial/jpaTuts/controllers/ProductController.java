package com.tusaryan.jpaTutorial.jpaTuts.controllers;

import com.tusaryan.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.tusaryan.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    //we should always follow mvc architecture
    //it is just for testing purpose that we are injecting product repo directly

    private final ProductRepository productRepository;

    //to initialize and inject the product repo dependency here
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productRepository.findByTitleOrderByPrice("Mazza");
    }
}
