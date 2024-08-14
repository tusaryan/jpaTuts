package com.tusaryan.jpaTutorial.jpaTuts.controllers;

import com.tusaryan.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.tusaryan.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {
    //hard code this size variable so that it cannot be changed

    private final int PAGE_SIZE = 5;


    //we should always follow mvc architecture
    //it is just for testing purpose that we are injecting product repo directly

    private final ProductRepository productRepository;

    //to initialize and inject the product repo dependency here
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //now loosely coupled as we can sort any field. now we don't need to write separate code to sort other field.
    @GetMapping
    public List<ProductEntity> getAllProducts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "0") Integer pageNumber) { //if nothing is provided then default value is passed i.e. "id".



//    public List<ProductEntity> getAllProducts(@RequestParam(defaultValue = "id") Optional<String> sortBy) {//using Optional, else we have to pass the default also.
        //to pass a sort fields/property, first we need to create it using Sort.by then pass it.

//        return productRepository.findBy(Sort.by(sortBy));//By-default sort on ascending order basis, to change it use Sort Direction

//        return productRepository.findBy(Sort.by(Sort.Direction.DESC, sortBy, "price", "quantity")); //working-> if we sort by title and two item have same title/same sortBy field then we sort them on the basis of price, if same price sort by quantity and so on.

//        return productRepository.findBy(Sort.by(
//                Sort.Order.desc(sortBy),
//                Sort.Order.asc("title") //if same sortBy field then sort by title in ascending order
//        )); //alternate way to create a sort object

        //use PageRequest class to create object/instance of Pageable.
        //Pageable pageable = PageRequest.of(pageNumber, size, Sort.by("lastName").ascending()); // sometimes its good practice to keep this number hard coded to your backend only and not give frontend access to it.
        //findAll work similar as findBy created earlier

//        Pageable pageable = PageRequest.of(
//                pageNumber,
//                PAGE_SIZE,
//                Sort.by(sortBy));
//        // .getContent get/take the content part of that page, and we return it.
//        // other getter like, to get size -> .getSize; .getTotalPages, .getFirst, .getTotalElements etc.
//        return productRepository.findAll(pageable).getContent();

        //we are using all we have learned so far
        //first of all we are using filters
        return productRepository.findByTitleContainingIgnoreCase(
                title, //filer on the basis of title
                PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy)) //we are getting paginated result and inside this pagination we have sorting as well
        );
    }
}
