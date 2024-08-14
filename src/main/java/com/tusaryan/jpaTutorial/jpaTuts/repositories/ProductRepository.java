package com.tusaryan.jpaTutorial.jpaTuts.repositories;

import com.tusaryan.jpaTutorial.jpaTuts.entities.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//this JpaRepository is implement by this class : SimpleJpaRepository, and it contains all those methods
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    //Can use Auto generation methods defined inside JPA for queries.
//    List<ProductEntity> findByOrderByPrice();//if we don't want to pass any field (i.e. want to check in all field) then we can use findBy instead of findAll
    //Above was a tightly coupled code so, if you want to sort by other field your have to write different code so to overcome this use the below method

    //now we are loosely coupled
//    List<ProductEntity> findByTitle(Sort sort);


    //use camel case to name here, like variable name in java object was "createdAt" -> CreatedAt
    List<ProductEntity> findByCreatedAtAfterOrderByTitle(LocalDateTime after);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    // we can also pass pageable to all auto generated function created by hibernate and instead of a Pageable it will return a List Object
    List<ProductEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
    //Using Optional to handle null pointer exception
    //upto here, all are auto generated method of hibernate.

    //To define own complex query -> either JPQL or SQL as well.
    //this is JPQL. passing the variables names we use that ?. so that queries are safe and no one can do SQL Injection attack on our queries.
//    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2") //JPQL understands JAVA only. Note here at this level we are not using "title_x", as name of the field JPQL understand is "title".

    //or Custom queries. this is JPQL query
    @Query("select e.title from ProductEntity e where e.title=:title and e.price=:price")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);
    //SQL -> select *
    //JPQL -> select e, Means return everything. OR "e.title" -> to get/return the title only
}
